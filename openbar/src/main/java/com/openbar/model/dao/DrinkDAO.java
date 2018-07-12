package com.openbar.model.dao;

import java.util.List;

import com.openbar.model.Drink;

public interface DrinkDAO {
	
	public List<Drink> getAvailableDrinks();
	public List<Drink> getDrinksOfAllOrders();
	public List<Drink> getDrinksOfAllOrdersByEmail(String email);
	public Drink getDrinkByDrinkId(Long drinkId);
	public void updateDrink(Drink drink);

}
