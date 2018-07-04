package openbardir.openbardir.drink;

import java.util.List;

public interface DrinkDAO {
	
	public List<Drink> getAvailableDrinks();
	public List<Drink> getDrinksOfAllOrders();
	public List<Drink> getDrinksOfAllOrdersByEmail(String email);
	public Drink getDrinkByDrinkId(Long drinkId);

}
