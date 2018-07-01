package jdbcIntegrationTesting;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import openbardir.openbardir.drink.Drink;
import openbardir.openbardir.drink.JDBCDrinkDAO;


public class JDBCDrinkDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCDrinkDAO drinkDAO;
	private JdbcTemplate jdbcTemplate;
	private Drink testDrink;
	
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
		drinkDAO = new JDBCDrinkDAO(dataSource);
		
		testDrink = new Drink();
		testDrink.setBrand("Brand");
		testDrink.setCategory("Beer");
		testDrink.setName("Name");
		testDrink.setPrice(3.00);
		testDrink.setType("Type");
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
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
	
	

}
