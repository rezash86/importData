package com.reza.importdata.setup.impl.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reza.importdata.model.MarketPrice;

public class MarketPriceMapper implements RowMapper<MarketPrice> {

	public MarketPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new MarketPrice(rs.getTimestamp("originaldatetime").toLocalDateTime(),
				rs.getString("hubname"), 
				rs.getFloat("lmp"),
				rs.getFloat("loss"),
				rs.getFloat("congestion"));
	}
}
