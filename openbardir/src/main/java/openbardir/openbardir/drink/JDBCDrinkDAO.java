package openbardir.openbardir.drink;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCDrinkDAO implements DrinkDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCDrinkDAO(DataSource dataSource) { 
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public List<Drink> getAvailableDrinks() {
		List<Drink> availableDrinks = new ArrayList<Drink>();
		String sqlSelectAvaiableDrinks = "select * from drink where is_available = true order by category, name";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAvaiableDrinks);
		while (result.next()) {
			availableDrinks.add(mapRowToDrink(result));
		}
		return availableDrinks;
	}
	
	public List<Drink> getDrinksOfAllOrders() {
		List<Drink> allOrderedDrinks = new ArrayList<Drink>();
		String sqlSelectAllOrderedDrinks = "select drink.drink_id, category, type, brand, name, price, is_available from drink \n" + 
				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
				"where is_available = true\n" + 
				"order by date_time desc";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllOrderedDrinks);
		while (result.next()) {
			allOrderedDrinks.add(mapRowToDrink(result));
		}
		return allOrderedDrinks;
	}

	public List<Drink> getDrinksOfAllOrdersByEmail(String email) {
		List<Drink> yourOrderedDrinks = new ArrayList<Drink>();
		String sqlSelectYourOrderedDrinks = "select drink.drink_id, category, type, brand, name, price, is_available from drink \n" + 
				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
				"where is_available = true and customer_email = ?\n" + 
				"order by date_time desc";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectYourOrderedDrinks, email);
		while (result.next()) {
			yourOrderedDrinks.add(mapRowToDrink(result));
		}
		return yourOrderedDrinks;
	}
	
	public Drink getDrinkByDrinkId(Long drinkId) {
		Drink drink = null;
		String sqlSelectDrinkByDrinkId = "select * from drink where drink_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectDrinkByDrinkId, drinkId);
		if (result.next()) {
			drink = mapRowToDrink(result);
		}
		return drink;
	}
		
	private Drink mapRowToDrink(SqlRowSet result) {
		Drink drink = new Drink();
		drink.setDrinkId(result.getLong("drink_id"));
		drink.setAvailable(result.getBoolean("is_available"));
		drink.setBrand(result.getString("brand"));
		drink.setCategory(result.getString("category"));
		drink.setName(result.getString("name"));
		drink.setPrice(result.getDouble("price"));
		drink.setType(result.getString("type"));
		return drink;
	}



	
	

}
