package com.openbar.unused;

import java.util.List;

import com.openbar.model.Drink;

public interface DrinkDAO {
	
	public Drink getDrinkByDrinkId(Long drinkId);
	public List<Drink> getAvailableDrinks(long barId);
	
//	public List<Drink> getDrinksOfAllOrders();
//	public List<Drink> getDrinksOfAllOrdersByEmail(String email);
//	
//	public void updateDrink(Drink drink);

}
