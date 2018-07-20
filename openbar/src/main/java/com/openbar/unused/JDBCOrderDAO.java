package com.openbar.unused;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.openbar.model.Order;


public class JDBCOrderDAO implements OrderDAO {
	
//	JdbcTemplate jdbcTemplate;
//	
//	public JDBCOrderDAO(DataSource dataSource) {
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}
//
//	public long submitOrder(Order order) {
//		String sqlInsertOrder = "insert into purchase_order (drink_id, customer_email, quantity, comment, filled_by_id) values (?, ?, ?, ?, ?) returning purchase_order_id";
//		return jdbcTemplate.queryForObject(sqlInsertOrder, Long.class, order.getDrinkId(), order.getEmail(), order.getQuantity(), order.getComment(), order.getStatus());
//	}
//
//	public List<Order> getAllOrders() {
//		List<Order> orders = new ArrayList<Order>();
//		String sqlSelectAllOrders = "select * from purchase_order order by date_time";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllOrders);
//		while (result.next()) {
//			orders.add(mapRowToOrder(result));
//		}
//		return orders;
//	}
//
//	public List<Order> getAllOrdersByEmail(String email) {
//		List<Order> orders = new ArrayList<Order>();
//		String sqlSelectOrdersByEmail = "select * from purchase_order where customer_email = ? order by date_time";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrdersByEmail, email);
//		while (result.next()) {
//			orders.add(mapRowToOrder(result));
//		}
//		return orders;
//	}
//	
//	public double getCostofOrderByOrderId(long orderId) {
//		String sqlCalcCostOfOrderById = "select quantity * price as cost from drink \n" + 
//				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
//				"where purchase_order_id = ?";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCalcCostOfOrderById, orderId);
//		double cost = 0;
//		if(result.next()) {
//			cost = result.getDouble("cost");
//		}
//		return cost;
//	}
//	
//	public int getNumberOfDrinksInQueue() {
//		int totalQuantity = 0;
//		String sqlSumQuantityUnfilledDrinks = "select sum(quantity) from purchase_order where filled_by_id is null";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSumQuantityUnfilledDrinks);
//		if (result.next()) {
//			totalQuantity = result.getInt("sum");
//		}
//		return totalQuantity;
//	}
//	
//	public List<Order> getUnfilledOrders() {
//		List<Order> orders = new ArrayList<Order>();
//		String sqlSelectUnfilledOrders = "select * from purchase_order where filled_by_id is null order by date_time";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectUnfilledOrders);
//		while (result.next()) {
//			orders.add(mapRowToOrder(result));
//		}
//		return orders;
//	}
//	
//	public Order getOrderByOrderId(long orderId) {
//		Order order = null;
//		String sqlSelectOrderByOrderId = "select * from purchase_order where purchase_order_id = ?";
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrderByOrderId, orderId);
//		if (result.next()) {
//			order = mapRowToOrder(result);
//		}
//		return order;
//	}
//	
//	private Order mapRowToOrder(SqlRowSet result) {
//		Order order = new Order();
//		order.setComment(result.getString("comment"));
//		order.setDateTime(result.getTimestamp("date_time"));
//		order.setDrinkId(result.getLong("drink_id"));
//		order.setEmail(result.getString("customer_email"));
//		order.setStatus(result.getString("status"));
//		order.setOrderId(result.getLong("purchase_order_id"));
//		order.setQuantity(result.getInt("quantity"));
//		return order;
//	}
//
//	public void updateOrder(Order order) {
//		String sqlUpdateDrinkId = "update purchase_order set drink_id = ? where purchase_order_id = ?";
//		String sqlUpdateEmail = "update purchase_order set customer_email = ? where purchase_order_id = ?";
//		String sqlUpdateQuantity = "update purchase_order set quantity = ? where purchase_order_id = ?";
//		String sqlUpdateComment = "update purchase_order set comment = ? where purchase_order_id = ?";
//		String sqlUpdateStatus = "update purchase_order set status = ? where purchase_order_id = ?";
//		String sqlUpdateDateTime = "update purchase_order set date_time = ? where purchase_order_id = ?";
//		jdbcTemplate.update(sqlUpdateDrinkId, order.getDrinkId(), order.getOrderId());
//		jdbcTemplate.update(sqlUpdateEmail, order.getEmail(), order.getOrderId());
//		jdbcTemplate.update(sqlUpdateQuantity, order.getQuantity(), order.getOrderId());
//		jdbcTemplate.update(sqlUpdateComment, order.getComment(), order.getOrderId());
//		jdbcTemplate.update(sqlUpdateStatus, order.getStatus(), order.getOrderId());
//		jdbcTemplate.update(sqlUpdateDateTime, order.getDateTime(), order.getOrderId());
//	}

	

	

	

	

	

}
