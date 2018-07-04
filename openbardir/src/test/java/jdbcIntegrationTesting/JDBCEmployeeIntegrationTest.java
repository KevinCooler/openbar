package jdbcIntegrationTesting;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import openbardir.openbardir.employee.Employee;
import openbardir.openbardir.employee.JDBCEmployeeDAO;

public class JDBCEmployeeIntegrationTest extends JDBCIntegrationParent {
	
	private JdbcTemplate jdbcTemplate;
	private JDBCEmployeeDAO employeeDAO;
	private Employee testEmployee;
	
	@Before
	public void setup() {
		DataSource dataSource = getDataSource();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.employeeDAO = new JDBCEmployeeDAO(dataSource);
	}
	
	@Test
	public void returns_first_name_of_test_employee() {
		long testEmployeeId = insertTestEmployee();
		Employee employee = employeeDAO.lookupEmpoyeeByEmployeeId(testEmployeeId);
		Assert.assertEquals("test",  employee.getFirstName());
	}

}
