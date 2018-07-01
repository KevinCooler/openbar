package openbardir.openbardir.order;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCOrderDAO implements OrderDAO {
	
	JdbcTemplate jdbcTemplate;
	
	public JDBCOrderDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public long submitOrder(Order order) {
		String sqlInsertOrder = "insert into purchase_order (drink_id, customer_email, quantity, comment) values (?, ?, ?, ?) returning purchase_order_id";
		return jdbcTemplate.queryForObject(sqlInsertOrder, Long.class, order.getDrinkId(), order.getEmail(), order.getQuantity(), order.getComment());
	}

}
