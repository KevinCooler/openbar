package openbardir.openbardir;

import java.util.List;

public interface DrinkDAO {
	
	public void save(Drink drink);
	public Drink findDrinkByOrderId(long purchaseOrderId);

}
