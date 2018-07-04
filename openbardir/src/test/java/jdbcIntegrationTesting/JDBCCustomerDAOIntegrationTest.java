package jdbcIntegrationTesting;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import openbardir.openbardir.customer.Customer;
import openbardir.openbardir.customer.JDBCCustomerDAO;


public class JDBCCustomerDAOIntegrationTest extends JDBCIntegrationParent{
	
	private JDBCCustomerDAO customerDAO;
	private JdbcTemplate jdbcTemplate;
	private Customer testCustomer;
	
	@Before
	public void setup() {
		DataSource dataSource = getDataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		customerDAO = new JDBCCustomerDAO(dataSource);
		
		testCustomer = new Customer();
		testCustomer.setCreditCardNumber("0000000000000000");
		testCustomer.setEmail("test@gmail.com");
		testCustomer.setName("Test");
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
