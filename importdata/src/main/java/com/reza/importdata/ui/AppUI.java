package com.reza.importdata.ui;

import java.time.LocalDateTime;
import java.util.List;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.common.ApplicationConstants;
import com.reza.importdata.common.CustomMessageLog;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.ImportDbService;

public class AppUI {

	public void doJob() {
		LocalDateTime startTime = LocalDateTime.now();
		while (LocalDateTime.now().compareTo(startTime.plusMinutes(ApplicationConstants.duration)) < 1){
			List<MarketPrice> data = getImportService().fetchData();
			if(!data.isEmpty()) {
				importData (data);
			}
			try {
				Thread.sleep(300000);
				//Thread.sleep(3000);
			} catch (InterruptedException e) {
				CustomMessageLog.showLog(e.getMessage());
			}
		}
	}
	
	
	private void importData(List<MarketPrice> marketPrices) {
		boolean importData = getImportService().importData(marketPrices);
		if (importData) {
			CustomMessageLog.showLog(ApplicationConstants.MESSAGE_SUCCESSFUL_IMPORT);
		} else {
			CustomMessageLog.showLog(ApplicationConstants.MESSAGE_UNSUCCESSFUL_IMPORT);
		}
	}

	protected IImportDbService getImportService() {
		return (ImportDbService) AppContext.getInstance().getContext().getBean(ImportDbService.class);
	}
}
