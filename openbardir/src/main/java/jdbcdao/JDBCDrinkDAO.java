package jdbcdao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import openbardir.openbardir.Drink;
import openbardir.openbardir.DrinkDAO;

public class JDBCDrinkDAO implements DrinkDAO {
	
	private List<Drink> drinks;
	private JdbcTemplate jdbcTemplate;
	
	public JDBCDrinkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(Drink drink) {
		// TODO Auto-generated method stub
		
	}

	public List<Drink> findDrinkByOrderId(long purchaseOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
