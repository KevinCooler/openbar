package openbardir.openbardir.drink;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCDrinkDAO implements DrinkDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCDrinkDAO(DataSource dataSource) { 
		this.jdbcTemplate = new JdbcTemplate(dataSource); 
	}

	public List<Drink> getAvailableDrinks() {
		List<Drink> availableDrinks = new ArrayList<Drink>();
		String sqlSelectAvaiableDrinks = "select * from drink where is_available = true";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAvaiableDrinks);
		while (result.next()) {
			availableDrinks.add(mapRowToDrink(result));
		}
		return availableDrinks;
	}
	
	private Drink mapRowToDrink(SqlRowSet result) {
		Drink drink = new Drink();
		drink.setDrinkId(result.getLong("drink_id"));
		drink.setAvailable(result.getBoolean("is_available"));
		drink.setBrand(result.getString("brand"));
		drink.setCategory(result.getString("category"));
		drink.setName(result.getString("name"));
		drink.setPrice(result.getDouble("price"));
		drink.setType(result.getString("type"));
		return drink;
	}

}
