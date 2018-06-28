package jdbcdao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import openbardir.openbardir.PurchaseOrderDAO;

public class JDBCPurchaseOrderDAO implements PurchaseOrderDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCPurchaseOrderDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

}
