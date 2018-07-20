package com.openbar.model.dao;

import java.util.List;

import com.openbar.model.DrinkOrder;

public interface DrinkOrderDAO {
	
	public List<DrinkOrder> getDrinkOrdersByBarId(long barId);

	public List<DrinkOrder> getDrinkOrdersOfCustomerAtBar(Long barId, String email);

}
