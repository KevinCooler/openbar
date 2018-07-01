package openbardir.openbardir.customer;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCCustomerDAO implements CustomerDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCustomerDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String createCustomerAccount(Customer customer) {
		String sqlInsertCustomer = "insert into customer (email, credit_card_number, name) values(?, ?, ?) returning email";
		String email = jdbcTemplate.queryForObject(sqlInsertCustomer, String.class, customer.getEmail(), customer.getCreditCardNumber(), customer.getName());
		return email;
	}

	public Customer lookupCustomerAccountByEmail(String email) {
		String sqlSelectCustomerByEmail = "select * from customer where email = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectCustomerByEmail, email);
		Customer customer = null;
		if(result.next()) {
			customer = mapRowToCustomer(result); 
		}
		return customer;
	}
	
	public Customer updateCustomerAccount(Customer customer) {
		String sqlUpdateCustomerCreditCardNumber = "update customer set credit_card_number = ? where email = ?";
		String sqlUpdateCustomerName = "update customer set name = ? where email = ?";
		jdbcTemplate.update(sqlUpdateCustomerCreditCardNumber, customer.getCreditCardNumber(), customer.getEmail());
		jdbcTemplate.update(sqlUpdateCustomerName, customer.getName(), customer.getEmail());
		
		return lookupCustomerAccountByEmail(customer.getEmail());
	}
	
	private Customer mapRowToCustomer(SqlRowSet result) {
		Customer customer = new Customer();
		customer.setEmail(result.getString("email"));
		customer.setCreditCardNumber(result.getString("credit_card_number"));
		customer.setName(result.getString("name"));
		return customer;
	}

	

}
