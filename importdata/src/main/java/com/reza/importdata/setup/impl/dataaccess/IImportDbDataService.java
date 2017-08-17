package com.reza.importdata.setup.impl.dataaccess;

import java.time.LocalDateTime;

import com.reza.importdata.model.MarketPrice;

public interface IImportDbDataService {
	
	public int createData(MarketPrice marketprice);
	
	public boolean dataExists(LocalDateTime marketPriceTime);
	
	public MarketPrice getData(LocalDateTime marketPriceTime);
}
