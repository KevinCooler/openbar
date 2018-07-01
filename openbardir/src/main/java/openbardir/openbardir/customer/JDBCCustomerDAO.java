package openbardir.openbardir.customer;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCCustomerDAO implements CustomerDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public JDBCCustomerDAO(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String createCustomerAccount(Customer customer) {
		String sqlInsertCustomer = "insert into customer (email, credit_card_number, name) values(?, ?, ?) returning email";
		String email = jdbcTemplate.queryForObject(sqlInsertCustomer, String.class, customer.getEmail(), customer.getCreditCardNumber(), customer.getName());
		return email;
	}

}
