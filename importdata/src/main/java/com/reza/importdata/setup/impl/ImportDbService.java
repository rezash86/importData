package com.reza.importdata.setup.impl;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.dataaccess.IImportDbDataService;
import com.reza.importdata.setup.impl.dataaccess.impl.ImportDbDataService;

@Component
public class ImportDbService implements IImportDbService {

	public boolean importData(List<MarketPrice> marketPrices) {
		if (marketPrices.size() > 0) {
			MarketPrice marketPrice = marketPrices.get(0);
			MarketPrice data = getImportDataDbService().getData(marketPrice.getOriginalDateTime());
			if (data != null) {

			} else {
				Optional<MarketPrice> falseImport = marketPrices.stream()
						.filter(mp -> getImportDataDbService().createData(mp) != 1).findAny();
				if (falseImport.isPresent()) {
					return false;
				} else {
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public List<MarketPrice> fetchData() {

		List<MarketPrice> marketPrices = new ArrayList<MarketPrice>();
		String feedUrl = "https://www.misoenergy.org/ria/LMP.aspx?query=udstable&format=xml";

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			MarketPrice marketPrice = new MarketPrice();

			builder = builderFactory.newDocumentBuilder();
			URL url = new URL(feedUrl);
			URLConnection conn = url.openConnection();
			Document doc = builder.parse(conn.getInputStream());
			NodeList nodesLevel0 = doc.getChildNodes();
			if (nodesLevel0 != null & nodesLevel0.getLength() > 0) {
				Element nodesLevel1 = (Element) nodesLevel0.item(0);
				String refId = nodesLevel1.getAttribute("RefId");
				
				if(nodesLevel1.getChildNodes() != null && nodesLevel1.getChildNodes().getLength() > 1) {
					Element nodesLevel2 = (Element) nodesLevel1.getChildNodes().item(1);
					String hourAndMin = nodesLevel2.getAttribute("HourAndMin");
					if(nodesLevel2.getChildNodes() != null && nodesLevel2.getChildNodes().getLength() > 1) {
						
						Element nodesLevel3 = (Element) nodesLevel2.getChildNodes().item(1);
						String lmpVal = nodesLevel3.getAttribute("LMP");
						String congestionVal = nodesLevel3.getAttribute("congestion");
						String lossVal = nodesLevel3.getAttribute("loss");
						String nameVal = nodesLevel3.getAttribute("name");
						
						marketPrice.setCongestion(Integer.parseInt(congestionVal));
						marketPrice.setLoss(Integer.parseInt(lossVal));
						marketPrice.setHubname(Integer.parseInt(nameVal));
						marketPrice.setLmp(Integer.parseInt(lmpVal));
					}
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	protected IImportDbDataService getImportDataDbService() {
		return (ImportDbDataService) AppContext.getInstance().getContext().getBean(ImportDbDataService.class);
	}

}