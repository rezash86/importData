package com.reza.importdata;

import java.time.LocalDateTime;
import java.util.List;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.common.ApplicationConstants;
import com.reza.importdata.common.CustomMessageLog;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.ImportDbService;

public class JobImport {
	
	/**
	 * This function does the import job
	 * for the duration being sent as a parameter
	 * from Now until Now + duration(min)
	 * it imports data from the web service
	 * @param duration
	 */
	public void doJob(Long duration) {
		LocalDateTime startTime = LocalDateTime.now();
		while (LocalDateTime.now().compareTo(startTime.plusMinutes(duration)) < 1){
			List<MarketPrice> data = getImportService().fetchData();
			if(!data.isEmpty()) {
				importData (data);
			}
			try {
				//Application stops working based on the config interval time
				Thread.sleep(ApplicationConstants.REFRESH_DATA_INTERVAL);
			} catch (InterruptedException e) {
				CustomMessageLog.showLog(e.getMessage());
			}
		}
		CustomMessageLog.showLog(ApplicationConstants.MESSAGE_FINISH_IMPORT);
	}
	
	/**
	 * if all the data imported successfully
	 * it writes in the output 'successful'
	 * otherwise writes 'unsuccessful'
	 * @param marketPrices
	 */
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
