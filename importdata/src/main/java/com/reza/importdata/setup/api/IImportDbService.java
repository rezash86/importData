package com.reza.importdata.setup.api;

import java.util.List;

import com.reza.importdata.model.MarketPrice;

public interface IImportDbService {
	
	/**
	 * This function fetches data from webService
	 * and put the data in the List of  MarketPrice Dto
	 * @return List
	 */
	public List<MarketPrice> fetchData();
	
	/**
	 * This function import the data has been fetched from
	 * web service and insert the data in the db if there 
	 * is no row at that timestamp
	 * @param marketPrices
	 * @return True if it imports all the data correctly
	 */
	public boolean importData(List<MarketPrice> marketPrices);
}
