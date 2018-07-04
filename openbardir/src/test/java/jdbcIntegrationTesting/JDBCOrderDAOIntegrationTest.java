package jdbcIntegrationTesting;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import openbardir.openbardir.order.JDBCOrderDAO;
import openbardir.openbardir.order.Order;


public class JDBCOrderDAOIntegrationTest extends JDBCIntegrationParent{
	
	private DataSource dataSource;
	private JDBCOrderDAO orderDAO;
	private JdbcTemplate jdbcTemplate;
	private Order testOrder;
	private long drinkId;
	private String email;
	
	@Before
	public void setup() {
		this.dataSource = getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		orderDAO = new JDBCOrderDAO(dataSource);
		
		//insert test drink
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type) values ('test', 'Beer', 'test', 3, 'test') returning drink_id";
		drinkId = jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class);
		
		//insert test customer
		String sqlInsertTestCustomer = "insert into customer (email, credit_card_number, name) values ('test@gmail.com', 1111222233334444, 'test') returning email";
		email = jdbcTemplate.queryForObject(sqlInsertTestCustomer, String.class);
		
		testOrder = new Order();
		testOrder.setDrinkId(drinkId);
		testOrder.setEmail(email);
		testOrder.setQuantity(9);
		testOrder.setComment("test comment");
		
	}

	@Test
	public void returns_orderId_of_submitted_order() {
		long orderId = orderDAO.submitOrder(testOrder);
		String email = null;
		String sqlSelectOrderByOrderId = "select customer_email from purchase_order where purchase_order_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectOrderByOrderId, orderId);
		if(result.next()) {
			email = result.getString("customer_email");
		}
		Assert.assertEquals("test@gmail.com", email);
	}
	
	@Test
	public void returns_order_that_was_submitted() {
		long orderId = orderDAO.submitOrder(testOrder);
		List<Order> orders = orderDAO.getAllOrders();
		boolean inList = false;
		for (Order each: orders) {
			if(each.getOrderId() == orderId) inList = true;
		}
		Assert.assertTrue(inList);
	}
	
	@Test
	public void returns_order_submitted_with_email() {
		long orderId = orderDAO.submitOrder(testOrder);
		List<Order> orders = orderDAO.getAllOrdersByEmail("test@gmail.com");
		boolean inList = false;
		for (Order each: orders) {
			if(each.getOrderId() == orderId) inList = true;
		}
		Assert.assertTrue(inList);
	}
	
	@Test
	public void not_return_order_submitted_with_different_email() {
		long orderId = orderDAO.submitOrder(testOrder);
		List<Order> orders = orderDAO.getAllOrdersByEmail("other@gmail.com");
		boolean inList = false;
		for (Order each: orders) {
			if(each.getOrderId() == orderId) inList = true;
		}
		Assert.assertFalse(inList);
	}
	
	@Test 
	public void returns_cost_as_18() {
		long orderId = orderDAO.submitOrder(testOrder);
		double cost = orderDAO.getCostofOrderByOrderId(orderId);
		
		Assert.assertEquals(new Double(27), new Double(cost));
	}
	
	@Test
	public void returns_count_plus_quantity_for_unfilled_order_added() {
		int startCount = orderDAO.getNumberOfDrinksInQueue();
		orderDAO.submitOrder(testOrder);
		int count = orderDAO.getNumberOfDrinksInQueue();
		
		Assert.assertEquals(startCount + testOrder.getQuantity(),  count);
	}
	
	@Test
	public void returns_same_count_for_filled_order_added() {
		int startCount = orderDAO.getNumberOfDrinksInQueue();
		testOrder.setFilledById(1l);
		orderDAO.submitOrder(testOrder);
		int count = orderDAO.getNumberOfDrinksInQueue();
		
		Assert.assertEquals(startCount,  count);
	}
	
	@Test
	public void returns_order_count_plus_1_for_unfilled_order_added() {
		int startCount = orderDAO.getUnfilledOrders().size();
		orderDAO.submitOrder(testOrder);
		int count = orderDAO.getUnfilledOrders().size();
		
		Assert.assertEquals(startCount + 1,  count);
	}
	
	@Test
	public void returns_same_order_count_for_filled_order_added() {
		int startCount = orderDAO.getUnfilledOrders().size();
		testOrder.setFilledById(1l);
		orderDAO.submitOrder(testOrder);
		int count = orderDAO.getUnfilledOrders().size();
		
		Assert.assertEquals(startCount,  count);
	}

}
