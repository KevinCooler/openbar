package openbardir.openbardir.employee;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCEmployeeDAO implements EmployeeDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Employee lookupEmpoyeeByEmployeeId(long employeeId) {
		Employee employee = null;
		String sqlSelectEmployeeByEmployeeId = "select * from employee where employee_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectEmployeeByEmployeeId, employeeId);
		if (result.next()) {
			employee = mapRowToEmployee(result);
		}
		return employee;
	}
	
	private Employee mapRowToEmployee(SqlRowSet result) {
			Employee employee = new Employee();
			employee.setEmployeeId(result.getLong("employee_id"));
			employee.setFirstName(result.getString("first_name"));
			employee.setLastName(result.getString("last_name"));
			return employee;
		}






}
