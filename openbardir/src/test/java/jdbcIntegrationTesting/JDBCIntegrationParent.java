package jdbcIntegrationTesting;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class JDBCIntegrationParent {
	
	private static SingleConnectionDataSource dataSource;
	
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
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return this.dataSource;
	}
	
	protected long insertTestEmployee() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sqlInsertTestEmployee = "insert into employee (first_name, last_name) values ('test', 'test') returning employee_id";
		long employeeId = jdbcTemplate.queryForObject(sqlInsertTestEmployee, Long.class);
		return employeeId;
	}
	
	
	
//	protected String addTestCustomer(JdbcTemplate jdbcTemplate) {
	
//		String sqlInsertTestCustomer = "insert into customer (email, credit_card_number, name) values('test@gmail.com', '0000000000000000', 'Test') returning email";
//		String email = jdbcTemplate.queryForObject(sqlInsertTestCustomer, String.class);
//		return email;
//	}

}
