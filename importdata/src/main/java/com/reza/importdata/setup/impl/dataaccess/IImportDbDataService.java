package com.reza.importdata.setup.impl.dataaccess;

import java.time.LocalDateTime;
import java.util.List;

import com.reza.importdata.model.MarketPrice;

public interface IImportDbDataService {
	
	/**
	 * this function returns data in the db
	 * if it returns 1 means successful
	 * otherwise DataAccessException means something is wrong
	 * @param marketprice
	 * @return int
	 */
	public int createData(MarketPrice marketprice);
	
	/**
	 * checks if the data exist in the db in the time
	 * @param marketPriceTime
	 * @return boolean
	 */
	public boolean dataExists(LocalDateTime marketPriceTime);
	
	/**
	 * Return list of the marketPrices
	 * @param marketPriceTime
	 * @return List
	 */
	public List<MarketPrice> getData(LocalDateTime marketPriceTime);

	/**
	 * Delete data from Db
	 * @param marketPriceTime
	 */
	void deleteData(LocalDateTime marketPriceTime);
	
}
