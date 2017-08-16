package com.reza.importdata.setup.impl.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.reza.importdata.model.MarketPrice;

public class MarketPriceMapper implements RowMapper<MarketPrice> {

	public MarketPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
		LocalDateTime originalDate = rs.getObject("originaldatetime", LocalDateTime.class);
		return new MarketPrice(originalDate, 
				rs.getString("hubname"), 
				rs.getFloat("lmp"),
				rs.getFloat("loss"),
				rs.getFloat("congestion"));
	}
}
