package com.reza.importdata.ui;

import java.util.List;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.ImportDbService;

public class AppUI {

	public void doJob() {
		List<MarketPrice> data = getImportService().fetchData();
		if(!data.isEmpty()) {
			importData(data);
		}
		
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
