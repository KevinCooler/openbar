package com.openbar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.openbar.model.Bar;

@Component
public class JDBCBarDAO implements BarDAO{
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCBarDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Bar> getListOfAllBars() {
		List<Bar> bars = new ArrayList<Bar>();
		String sqlSelectAllBars = "select * from bar";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllBars);
		while(result.next()) {
			bars.add(mapRowToBar(result));
		}
		return bars;
	}
	
	public Bar getBarByBarId(long barId) {
		Bar bar = new Bar();
		String sqlSelectBar = "select * from bar where bar_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectBar, barId);
		if(result.next()) {
			bar = mapRowToBar(result);
		}
		return bar;
	}
	
	private Bar mapRowToBar(SqlRowSet result) {
		Bar bar = new Bar();
		bar.setAccountNumber(result.getString("account_number"));
		bar.setBarId(result.getLong("bar_id"));
		bar.setName(result.getString("name"));
		return bar;
	}



}
