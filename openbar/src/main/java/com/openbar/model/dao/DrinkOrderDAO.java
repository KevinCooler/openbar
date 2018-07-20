package com.openbar.model.dao;

import java.util.List;

import com.openbar.model.DrinkOrder;

public interface DrinkOrderDAO {
	
	public List<DrinkOrder> getAvailableDrinkOrdersAtBar(Long barId);
	public List<DrinkOrder> getAvailableCurrentDrinkOrdersAtBar(long barId);
	public List<DrinkOrder> getAvailableDrinkOrdersOfCustomerAtBar(Long barId, String email);
	

}
