package com.openbar.unused;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.openbar.model.Drink;

public class JDBCDrinkDAO implements DrinkDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCDrinkDAO(DataSource dataSource) { 
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public List<Drink> getAvailableDrinks(long barId) {
		List<Drink> availableDrinks = new ArrayList<Drink>();
		String sqlSelectAvaiableDrinks = "select drink_id from bar_drink where is_available = true and bar_id =?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAvaiableDrinks, barId);
		while (result.next()) {
			availableDrinks.add(getDrinkByDrinkId(result.getLong("drink_id")));
		}
		return availableDrinks;
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
	
//	public List<Drink> getDrinksOfAllOrders() {
//		List<Drink> allOrderedDrinks = new ArrayList<Drink>();
//		String sqlSelectAllOrderedDrinks = "select drink.drink_id, category, type, brand, name, price, is_available from drink \n" + 
//				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
//				"where is_available = true\n" + 
//				"order by date_time";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllOrderedDrinks);
//		while (result.next()) {
//			allOrderedDrinks.add(mapRowToDrink(result));
//		}
//		return allOrderedDrinks;
//	}
//
//	public List<Drink> getDrinksOfAllOrdersByEmail(String email) {
//		List<Drink> yourOrderedDrinks = new ArrayList<Drink>();
//		String sqlSelectYourOrderedDrinks = "select drink.drink_id, category, type, brand, name, price, is_available from drink \n" + 
//				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
//				"where is_available = true and customer_email = ?\n" + 
//				"order by date_time";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectYourOrderedDrinks, email);
//		while (result.next()) {
//			yourOrderedDrinks.add(mapRowToDrink(result));
//		}
//		return yourOrderedDrinks;
//	}
//	
//	public void updateDrink(Drink drink) {
//		String sqlUpdateAvailable = "update drink set is_available = ? where drink_id = ?";
//		String sqlUpdateCategory = "update drink set category = ? where drink_id = ?";
//		String sqlUpdateType = "update drink set type = ? where drink_id = ?";
//		String sqlUpdateBrand = "update drink set brand = ? where drink_id = ?";
//		String sqlUpdateName = "update drink set name = ? where drink_id = ?";
//		String sqlUpdatePrice = "update drink set price = ? where drink_id = ?";
//		jdbcTemplate.update(sqlUpdateAvailable, drink.isAvailable(), drink.getDrinkId());
//		jdbcTemplate.update(sqlUpdateCategory, drink.getCategory(), drink.getDrinkId());
//		jdbcTemplate.update(sqlUpdateType, drink.getType(), drink.getDrinkId());
//		jdbcTemplate.update(sqlUpdateBrand, drink.getBrand(), drink.getDrinkId());
//		jdbcTemplate.update(sqlUpdateName, drink.getName(), drink.getDrinkId());
//		jdbcTemplate.update(sqlUpdatePrice, drink.getPrice(), drink.getDrinkId());
//
//	}
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
