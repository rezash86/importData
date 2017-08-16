package com.reza.importdata.setup.api;

import java.util.List;

import com.reza.importdata.model.MarketPrice;

public interface IImportDbService {
	
	public List<MarketPrice> fetchData();
	
	public boolean importData(List<MarketPrice> marketPrices);
}
