package jdbcIntegrationTesting;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import openbardir.openbardir.drink.Drink;
import openbardir.openbardir.drink.JDBCDrinkDAO;


public class JDBCDrinkDAOIntegrationTest extends JDBCIntegrationParent{
	
	private DataSource dataSource;
	private JDBCDrinkDAO drinkDAO;
	private JdbcTemplate jdbcTemplate;
	private Drink testDrink;
	
	@Before
	public void setup() {
		this.dataSource = getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		drinkDAO = new JDBCDrinkDAO(dataSource);
		
		testDrink = new Drink();
		testDrink.setBrand("Brand");
		testDrink.setCategory("Beer");
		testDrink.setName("Name");
		testDrink.setPrice(3.00);
		testDrink.setType("Type");
	}
	
	@Test
	public void returns_testDrink_in_list() {
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type) values (?, ?, ?, ?, ?) returning drink_id";
		long drinkId = jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class, testDrink.getBrand(), testDrink.getCategory(), testDrink.getName(), testDrink.getPrice(), testDrink.getType());
		testDrink.setDrinkId(drinkId);
		
		List<Drink> drinks = drinkDAO.getAvailableDrinks();
		boolean inList = false;
		for (Drink each: drinks) {
			if (each.equals(testDrink)) inList = true;
		}
		Assert.assertTrue(inList);
	}
	
	@Test
	public void does_not_return_unavailable_drink() {
		testDrink.setAvailable(false);
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type, is_available) values (?, ?, ?, ?, ?, ?) returning drink_id";
		long drinkId = jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class, testDrink.getBrand(), testDrink.getCategory(), testDrink.getName(), testDrink.getPrice(), testDrink.getType(), testDrink.isAvailable());
		testDrink.setDrinkId(drinkId);
		
		List<Drink> drinks = drinkDAO.getAvailableDrinks();
		boolean inList = false;
		for (Drink each: drinks) {
			if (each.equals(testDrink)) inList = true;
		}
		Assert.assertFalse(inList);
	}
	
	@Test
	public void returns_correct_count_of_avaiable_drinks() {
		String sqlCountAvailableDrinks = "select count(*) from drink where is_available = true";
		int count = 0;
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCountAvailableDrinks);
		if(result.next()) {
			count = result.getInt("count");
		}
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type) values (?, ?, ?, ?, ?) returning drink_id";
		jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class, testDrink.getBrand(), testDrink.getCategory(), testDrink.getName(), testDrink.getPrice(), testDrink.getType());
		
		List<Drink> drinks = drinkDAO.getAvailableDrinks();
		
		Assert.assertEquals(count + 1,  drinks.size());
	}
	
	@Test
	public void only_returns_drink_from_order_when_drink_available() {
		long drinkId = insertTestDrink();
		long unavailableDrinkId = insertUnavailableTestDrink();
		String email = insertTestCustomer();
		insertTestOrder(drinkId, email);
		insertTestOrder(unavailableDrinkId, email);
		boolean isInList = false;
		boolean notInList = true;
		List<Drink> drinks = drinkDAO.getDrinksOfAllOrders();
		
		for (Drink drink: drinks) {
			if(drink.getDrinkId() == drinkId) isInList = true;
			if(drink.getDrinkId() == unavailableDrinkId) notInList = false;
		}
		Assert.assertTrue(isInList);
		Assert.assertTrue(notInList);
	}
	
	@Test
	public void only_returns_drink_from_from_email() {
		long drinkId = insertTestDrink();
		String sqlInsertTestCustomer = "insert into customer (email, credit_card_number, name) values ('different@gmail.com', 1111222233334444, 'test') returning email";
		String differentEmail = jdbcTemplate.queryForObject(sqlInsertTestCustomer, String.class);
		String email = insertTestCustomer();
		insertTestOrder(drinkId, email);
		insertTestOrder(drinkId, differentEmail);
		List<Drink> drinks = drinkDAO.getDrinksOfAllOrdersByEmail(email);
		
		Assert.assertEquals(1,  drinks.size());
	}
	
	@Test
	public void returns_name_of_drink_searched_for() {
		long drinkId = insertTestDrink();
		Drink drink = drinkDAO.getDrinkByDrinkId(drinkId);
		
		Assert.assertEquals("test", drink.getName());
	}
	
	private long insertTestDrink() {
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type) values ('test', 'Beer', 'test', 33, 'test') returning drink_id";
		return jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class);
	}
	private long insertUnavailableTestDrink() {
		String sqlInsertTestDrink = "insert into drink (brand, category, name, price, type, is_available) values ('test', 'Beer', 'test', 33, 'test', false) returning drink_id";
		return jdbcTemplate.queryForObject(sqlInsertTestDrink, Long.class);
	}
	private String insertTestCustomer() {
		String sqlInsertTestCustomer = "insert into customer (email, credit_card_number, name) values ('test@gmail.com', 1111222233334444, 'test') returning email";
		return jdbcTemplate.queryForObject(sqlInsertTestCustomer, String.class);
	}
	private long insertTestOrder(long drinkId, String email) {
		String sqlInsertOrder = "insert into purchase_order (drink_id, customer_email, quantity, comment) values (?, ?, 5, 'test') returning purchase_order_id";
		return jdbcTemplate.queryForObject(sqlInsertOrder, Long.class, drinkId, email);
	}
	
	

}
