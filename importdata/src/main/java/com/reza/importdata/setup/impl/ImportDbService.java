package com.reza.importdata.setup.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.common.ApplicationConstants;
import com.reza.importdata.common.CustomMessageLog;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.dataaccess.IImportDbDataService;
import com.reza.importdata.setup.impl.dataaccess.impl.ImportDbDataService;

public class ImportDbService implements IImportDbService {

	public List<MarketPrice> fetchData() {
		List<MarketPrice> marketPrices = new ArrayList<MarketPrice>();
		try {
			
			Document doc = getDocument();
			NodeList nodesLevel0 = doc.getChildNodes();
			if (nodesLevel0 != null & nodesLevel0.getLength() > 0) {
				Element nodesLevel1 = (Element) nodesLevel0.item(0);
				String refDate = nodesLevel1.getAttribute(ApplicationConstants.REF_ID);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_TIME_PATTERN);
				LocalDateTime dateTime = LocalDateTime.parse(refDate, formatter);

				if (nodesLevel1.getChildNodes() != null && nodesLevel1.getChildNodes().getLength() > 1) {
					Element nodesLevel2 = (Element) nodesLevel1.getChildNodes().item(1);

					if (nodesLevel2.getChildNodes() != null && nodesLevel2.getChildNodes().getLength() > 1) {
						for (int index = 0; index < nodesLevel2.getChildNodes().getLength(); index++) {
							
							if ((index % 2) != 0) {
								Element nodesLevel3 = (Element) nodesLevel2.getChildNodes().item(index);
								MarketPrice marketPrice = getMarketPrice(nodesLevel3, dateTime);
								
								marketPrices.add(marketPrice);
							}
						}

					}
				}
			}
		} catch (ParserConfigurationException e) {
			CustomMessageLog.showLog(e.getMessage());
		} catch (IOException e) {
			CustomMessageLog.showLog(e.getMessage());
		} catch (SAXException e) {
			CustomMessageLog.showLog(e.getMessage());
		}

		return marketPrices;
	}
	
	/**
	 * This function returns a marketPrice based on the element and dataTime
	 * @param nodesLevel3
	 * @param dateTime
	 * @return
	 */
	private MarketPrice getMarketPrice(Element nodesLevel3, LocalDateTime dateTime){
		MarketPrice marketPrice = new MarketPrice();
		marketPrice.setOriginalDateTime(dateTime);
		String lmpVal = nodesLevel3.getAttribute(ApplicationConstants.LMP);
		String congestionVal = nodesLevel3.getAttribute(ApplicationConstants.CONGESTION);
		String lossVal = nodesLevel3.getAttribute(ApplicationConstants.LOSS);
		String nameVal = nodesLevel3.getAttribute(ApplicationConstants.HUB_NAME);

		marketPrice.setCongestion(Float.parseFloat(congestionVal));
		marketPrice.setLoss(Float.parseFloat(lossVal));
		marketPrice.setHubname(nameVal);
		marketPrice.setLmp(Float.parseFloat(lmpVal));
		
		return marketPrice;
	}
	
	
	/**
	 * This method generates a Document from the Feed_Url
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private Document getDocument() throws ParserConfigurationException, IOException, SAXException {
		String feedUrl = ApplicationConstants.FEED_URL;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		URL url = new URL(feedUrl);
		URLConnection conn = url.openConnection();
		return builder.parse(conn.getInputStream());
	}

	/**
	 * This function imports the marketPrices one by one
	 * if All the rows inserted in the db it thorows True
	 * otherwise it throws exception
	 */
	public boolean importData(List<MarketPrice> marketPrices) {
		if (marketPrices.size() > 0) {
			MarketPrice marketPrice = marketPrices.get(0);
			boolean dataExists = getImportDataDbService().dataExists(marketPrice.getOriginalDateTime());
			if (!dataExists) {
				Optional<MarketPrice> errorInsert = marketPrices.stream().filter(mp -> insertDb(mp) != 1).findAny();
				return !errorInsert.isPresent();
			}
			else{
				CustomMessageLog.showLog(ApplicationConstants.ERROR_DATA_EXISTS);
				return false;
			}
		}
		
		CustomMessageLog.showLog(ApplicationConstants.ERROR_CONNECTION_NOT_EXISTS);
		return false;
	}
	
	/**
	 * insert market prices one by one
	 * @param mp
	 * @return
	 */
	private int insertDb(MarketPrice mp) {
		return getImportDataDbService().createData(mp);
	}

	@Override
	public List<MarketPrice> getData(LocalDateTime marketPriceTime) {
		return getImportDataDbService().getData(marketPriceTime);
	}
	@Override
	public void deleteData(LocalDateTime marketPriceTime){
		getImportDataDbService().deleteData(marketPriceTime);
	}
	
	protected IImportDbDataService getImportDataDbService() {
		return (ImportDbDataService) AppContext.getInstance().getContext().getBean(ImportDbDataService.class);
	}

	
}
