package com.reza.importdata.ui;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.ImportDbService;

public class AppUI {

	public List<MarketPrice> fetchData()  {
		String feedUrl = "https://www.misoenergy.org/ria/LMP.aspx?query=udstable&format=xml";

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
			URL url = new URL(feedUrl);
	        URLConnection conn = url.openConnection();
			Document doc = builder.parse(conn.getInputStream());
			Element elementById = doc.getElementById("LMPData");
			System.out.println(doc.getDocumentURI());
			
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

	public void importDate(List<MarketPrice> marketPrices) {
		boolean importData = getImportService().importData(marketPrices);
		if (importData) {
			System.out.println("Successfull import");
		} else {
			System.out.println("Unsuccessfull import");
		}
	}

	protected IImportDbService getImportService() {
		return (ImportDbService) AppContext.getInstance().getContext().getBean(ImportDbService.class);
	}
}
