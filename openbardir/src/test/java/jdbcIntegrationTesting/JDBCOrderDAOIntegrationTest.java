package jdbcIntegrationTesting;

import java.sql.SQLException;

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


public class JDBCOrderDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCOrderDAO orderDAO;
	private JdbcTemplate jdbcTemplate;
	private Order testOrder;
	private long drinkId;
	private String email;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/openbar");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		orderDAO = new JDBCOrderDAO(dataSource);
		
		//insert test drink
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type) values ('test', 'Beer', 'test', 33, 'test') returning drink_id";
		drinkId = jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class);
		
		//insert test customer
		String sqlInsertTestCustomer = "insert into customer (email, credit_card_number, name) values ('test@gmail.com', 1111222233334444, 'test') returning email";
		email = jdbcTemplate.queryForObject(sqlInsertTestCustomer, String.class);
		
		testOrder = new Order();
		testOrder.setDrinkId(drinkId);
		testOrder.setEmail(email);
		testOrder.setQuantity(99);
		testOrder.setComment("test comment");
		
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
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

}
