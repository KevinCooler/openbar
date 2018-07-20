package com.openbar.model.dao;

import java.util.List;

import com.openbar.model.Bar;

public interface BarDAO {
	
	public List<Bar> getListOfAllBars();

	public Bar getBarByBarId(long barId);

}
