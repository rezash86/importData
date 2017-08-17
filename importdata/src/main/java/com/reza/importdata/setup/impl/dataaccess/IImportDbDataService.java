package com.reza.importdata.setup.impl.dataaccess;

import java.time.LocalDateTime;

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
	
}
