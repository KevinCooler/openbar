package com.openbar.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.openbar.model.DrinkOrder;

@Component
public class JDBCDrinkOrderDAO implements DrinkOrderDAO{
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCDrinkOrderDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<DrinkOrder> getAvailableCurrentDrinkOrdersAtBar(long barId) {
		List<DrinkOrder> drinkOrders = new ArrayList<DrinkOrder>();
		String sqlSelectDrinkOrdersByBarId = "select po.bar_id, is_available, brand, category, comment, customer.name as customer_name, \n" + 
				"        date_time, drink.drink_id, drink.name as drink_name, customer.email, \n" + 
				"        purchase_order_id, price, quantity, is_special, status, type\n" + 
				"                from purchase_order po\n" + 
				"                join drink on drink.drink_id = po.drink_id \n" + 
				"                join customer on po.email = customer.email\n" + 
				"                join bar_drink on bar_drink.drink_id = po.drink_id\n" + 
				"                where po.bar_id = ? and bar_drink.bar_id = ? and is_available = true\n" + 
				"                order by date_time desc";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectDrinkOrdersByBarId, barId, barId);
		while(result.next()) {
			drinkOrders.add(mapRowToDrinkOrder(result));
		}
		return drinkOrders;
	}
	
	public List<DrinkOrder> getAvailableDrinkOrdersOfCustomerAtBar(Long barId, String email) {
		List<DrinkOrder> drinkOrders = new ArrayList<DrinkOrder>();
		String sqlSelectDrinkOrdersByBarIdForCustomer = "select po.bar_id, is_available, brand, category, comment, customer.name as customer_name,\n" + 
				"	date_time, drink.drink_id, drink.name as drink_name, customer.email, purchase_order_id, \n" + 
				"	price, quantity, is_special, status, type \n" + 
				"                from purchase_order po\n" + 
				"                join drink on drink.drink_id = po.drink_id \n" + 
				"                join customer on po.email = customer.email\n" + 
				"                join bar_drink on bar_drink.drink_id = po.drink_id\n" + 
				"                where po.bar_id = ? and bar_drink.bar_id = ? and is_available = true and customer.email = ? \n" + 
				"                order by date_time desc";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectDrinkOrdersByBarIdForCustomer, barId, barId, email);
		while(result.next()) {
			drinkOrders.add(mapRowToDrinkOrder(result));
		}
		return drinkOrders;
	}
	
	public List<DrinkOrder> getAvailableDrinkOrdersAtBar(Long barId) {
		List<DrinkOrder> drinkOrders = new ArrayList<DrinkOrder>();
		String sqlSelectAvaialbleDrinks = "select bar_drink.bar_id, is_available, brand, category, drink.drink_id, \n" + 
				"        drink.name as drink_name, price, is_special, type \n" + 
				"                from bar_drink\n" + 
				"                join drink on drink.drink_id = bar_drink.drink_id\n" + 
				"                where bar_id = ? and is_available = true order by category , type, brand, drink_name";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAvaialbleDrinks, barId);
		while(result.next()) {
			drinkOrders.add(mapDrinkRowToDrinkOrder(result));
		}
		return drinkOrders;
	}

	private DrinkOrder mapDrinkRowToDrinkOrder(SqlRowSet result) {
		DrinkOrder drinkOrder = new DrinkOrder();
		drinkOrder.setAvailable(result.getBoolean("is_available"));
		drinkOrder.setBrand(result.getString("brand"));
		drinkOrder.setCategory(result.getString("category"));
		drinkOrder.setDrinkId(result.getLong("drink_id"));
		drinkOrder.setDrinkName(result.getString("drink_name"));
		drinkOrder.setPrice(result.getDouble("price"));
		drinkOrder.setSpecial(result.getBoolean("is_special"));
		drinkOrder.setType(result.getString("type"));
		drinkOrder.setBarId(result.getLong("bar_id"));
		return drinkOrder;
	}

	private DrinkOrder mapRowToDrinkOrder(SqlRowSet result) {
		DrinkOrder drinkOrder = new DrinkOrder();
		drinkOrder.setAvailable(result.getBoolean("is_available"));
		drinkOrder.setBrand(result.getString("brand"));
		drinkOrder.setCategory(result.getString("category"));
		drinkOrder.setComment(result.getString("comment"));
		drinkOrder.setCustomerName(result.getString("customer_name"));
		drinkOrder.setDateTime(result.getDate("date_time").toLocalDate());
		drinkOrder.setDrinkId(result.getLong("drink_id"));
		drinkOrder.setDrinkName(result.getString("drink_name"));
		drinkOrder.setEmail(result.getString("email"));
		drinkOrder.setOrderId(result.getLong("purchase_order_id"));
		drinkOrder.setPrice(result.getDouble("price"));
		drinkOrder.setQuantity(result.getInt("quantity"));
		drinkOrder.setSpecial(result.getBoolean("is_special"));
		drinkOrder.setStatus(result.getString("status"));
		drinkOrder.setType(result.getString("type"));
		drinkOrder.setBarId(result.getLong("bar_id"));
		return drinkOrder;
	}

	

	

}
