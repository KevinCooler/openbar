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


import openbardir.openbardir.customer.Customer;
import openbardir.openbardir.customer.JDBCCustomerDAO;


public class JDBCCustomerDAOIntegrationTest extends JDBCIntegrationParent{
	
	private static SingleConnectionDataSource dataSource;
	private JDBCCustomerDAO customerDAO;
	private JdbcTemplate jdbcTemplate;
	private Customer testCustomer;
	
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
		customerDAO = new JDBCCustomerDAO(dataSource);
		
		testCustomer = new Customer();
		testCustomer.setCreditCardNumber("0000000000000000");
		testCustomer.setEmail("test@gmail.com");
		testCustomer.setName("Test");
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void returns_email_of_inserted_customer_from_database() {		
		String email = customerDAO.createCustomerAccount(testCustomer);
		Assert.assertEquals("test@gmail.com",  email);
	}
	
	@Test
	public void return_user_name_from_email() {
		customerDAO.createCustomerAccount(testCustomer);
		Customer selectedCustomer = customerDAO.lookupCustomerAccountByEmail("test@gmail.com");
		Assert.assertEquals("Test",  selectedCustomer.getName());
	}
	
	@Test 
	public void return_updated_cc_number() {
		customerDAO.createCustomerAccount(testCustomer);
		testCustomer.setCreditCardNumber("999");
		Customer updatedCustomer = customerDAO.updateCustomerAccount(testCustomer);
		
		Assert.assertEquals("999",  updatedCustomer.getCreditCardNumber());
	}
	
	@Test 
	public void return_updated_name() {
		customerDAO.createCustomerAccount(testCustomer);
		testCustomer.setName("newName");
		Customer updatedCustomer = customerDAO.updateCustomerAccount(testCustomer);
		
		Assert.assertEquals("newName",  updatedCustomer.getName());
	}
	
	

}
