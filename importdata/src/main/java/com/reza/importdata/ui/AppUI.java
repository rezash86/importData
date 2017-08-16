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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.ImportDbService;

public class AppUI {

	public void doJob() {
		List<MarketPrice> data = getImportService().fetchData();
		importData(data);
	}
	
	
	private void importData(List<MarketPrice> marketPrices) {
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
