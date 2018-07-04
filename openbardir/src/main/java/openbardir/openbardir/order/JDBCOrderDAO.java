package openbardir.openbardir.order;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import openbardir.openbardir.drink.Drink;

public class JDBCOrderDAO implements OrderDAO {
	
	JdbcTemplate jdbcTemplate;
	
	public JDBCOrderDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long submitOrder(Order order) {
		String sqlInsertOrder = "insert into purchase_order (drink_id, customer_email, quantity, comment, filled_by_id) values (?, ?, ?, ?, ?) returning purchase_order_id";
		return jdbcTemplate.queryForObject(sqlInsertOrder, Long.class, order.getDrinkId(), order.getEmail(), order.getQuantity(), order.getComment(), order.getFilledById());
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		String sqlSelectAllOrders = "select * from purchase_order order by date_time";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllOrders);
		while (result.next()) {
			orders.add(mapRowToOrder(result));
		}
		return orders;
	}

	public List<Order> getAllOrdersByEmail(String email) {
		List<Order> orders = new ArrayList<Order>();
		String sqlSelectOrdersByEmail = "select * from purchase_order where customer_email = ? order by date_time";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrdersByEmail, email);
		while (result.next()) {
			orders.add(mapRowToOrder(result));
		}
		return orders;
	}
	
	public double getCostofOrderByOrderId(long orderId) {
		String sqlCalcCostOfOrderById = "select quantity * price as cost from drink \n" + 
				"join purchase_order on drink.drink_id = purchase_order.drink_id \n" + 
				"where purchase_order_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCalcCostOfOrderById, orderId);
		double cost = 0;
		if(result.next()) {
			cost = result.getDouble("cost");
		}
		return cost;
	}
	
	public int getNumberOfDrinksInQueue() {
		int totalQuantity = 0;
		String sqlSumQuantityUnfilledDrinks = "select sum(quantity) from purchase_order where filled_by_id is null";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSumQuantityUnfilledDrinks);
		if (result.next()) {
			totalQuantity = result.getInt("sum");
		}
		return totalQuantity;
	}
	
	public List<Order> getUnfilledOrders() {
		List<Order> orders = new ArrayList<Order>();
		String sqlSelectUnfilledOrders = "select * from purchase_order where filled_by_id is null order by date_time";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectUnfilledOrders);
		while (result.next()) {
			orders.add(mapRowToOrder(result));
		}
		return orders;
	}
	
	public Order getOrderByOrderId(long orderId) {
		Order order = null;
		String sqlSelectOrderByOrderId = "select * from purchase_order where purchase_order_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrderByOrderId, orderId);
		if (result.next()) {
			order = mapRowToOrder(result);
		}
		return order;
	}
	
	private Order mapRowToOrder(SqlRowSet result) {
		Order order = new Order();
		order.setComment(result.getString("comment"));
		order.setDateTime(result.getTimestamp("date_time"));
		order.setDrinkId(result.getLong("drink_id"));
		order.setEmail(result.getString("customer_email"));
		order.setFilledById(result.getLong("filled_by_id"));
		order.setOrderId(result.getLong("purchase_order_id"));
		order.setQuantity(result.getInt("quantity"));
		return order;
	}

	

	

	

	

	

}
