package com.reza.importdata.setup.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.reza.importdata.common.AppContext;
import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.api.IImportDbService;
import com.reza.importdata.setup.impl.dataaccess.IImportDbDataService;
import com.reza.importdata.setup.impl.dataaccess.impl.ImportDbDataService;

@Component
public class ImportDbService implements IImportDbService {

	public boolean importData(List<MarketPrice> marketPrices) {
		if(marketPrices.size() > 0){
			MarketPrice marketPrice = marketPrices.get(0);
			MarketPrice data = getImportDataDbService().getData(marketPrice.getOriginalDateTime());
			if(data != null){
				
			}else{
				Optional<MarketPrice> falseImport = marketPrices.stream().filter(mp -> getImportDataDbService().createData(mp) != 1).findAny();
				if(falseImport.isPresent()){
					return false;
				}
				else{
					return true;
				}
			}
		
		}
		return false;
	}
	
	
	protected IImportDbDataService getImportDataDbService() {
		return (ImportDbDataService) AppContext.getInstance().getContext().getBean(ImportDbDataService.class);
	}

}
