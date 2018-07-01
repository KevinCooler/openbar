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
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/openbar");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		customerDAO = new JDBCCustomerDAO(dataSource);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void returns_email_of_inserted_customer_from_database() {
		Customer customer = new Customer();
		customer.setCreditCardNumber("0000000000000000");
		customer.setEmail("test@gmail.com");
		customer.setName("Test");
		
		String email = customerDAO.createCustomerAccount(customer);
		
		Assert.assertEquals("test@gmail.com",  email);
	}

}
