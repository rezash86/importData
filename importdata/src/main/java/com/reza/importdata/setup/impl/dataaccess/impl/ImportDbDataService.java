package com.reza.importdata.setup.impl.dataaccess.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.reza.importdata.model.MarketPrice;
import com.reza.importdata.setup.impl.dataaccess.IImportDbDataService;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ImportDbDataService implements IImportDbDataService{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public int createData(MarketPrice marketprice) {
		String SQL = "insert into miso_market_price_five_minutes(originaldatetime, hubname, lmp, loss, congestion) "
				+ " values(?, ?, ?, ?, ?) ";
		return jdbcTemplateObject.update(SQL, 
				Timestamp.valueOf(marketprice.getOriginalDateTime()), 
				marketprice.getHubname(), 
				marketprice.getLmp(), 
				marketprice.getLoss(), 
				marketprice.getCongestion());
	}

	

	public boolean dataExists(LocalDateTime marketPriceTime) {
		String SQL = "select originaldatetime, hubname, lmp, loss, congestion from miso_market_price_five_minutes where originaldatetime=?";
		List<MarketPrice> marketPrices = jdbcTemplateObject.query(SQL, new Timestamp [] {Timestamp.valueOf(marketPriceTime)} , new MarketPriceMapper());
		if(!marketPrices.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	

}
