package com.reza.importdata.setup.impl.dataaccess.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.impl.dataaccess.IImportDbDataService;

import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ImportDbDataService implements IImportDbDataService{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public int createData(MarketPrice marketprice) {
		String SQL = "insert into miso_market_price_five_minutes(originaldatetime, hubname, lmp, loss, congestion) "
				+ " values(?, ?, ?, ?, ?) ";
		return jdbcTemplateObject.update(SQL, 
				marketprice.getOriginalDateTime(), 
				marketprice.getHubname(), 
				marketprice.getLmp(), 
				marketprice.getLoss(), 
				marketprice.getCongestion());
	}

	public MarketPrice getData(LocalDateTime marketPriceTime) {
		String SQL = "select originaldatetime, hubname, lmp, loss, congestion from miso_market_price_five_minutes where originaldatetime=?";
		List<MarketPrice> serverStatus = jdbcTemplateObject.query(SQL, new LocalDateTime[] {marketPriceTime}, new MarketPriceMapper());
		return serverStatus.get(0);
	}
	

}
