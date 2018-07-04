package openbardir.openbardir.order;

import java.util.List;


public interface OrderDAO {
	
	public long submitOrder(Order order);
	
	public List<Order> getAllOrders();
	public List<Order> getAllOrdersByEmail(String email);
	
	public double getCostofOrderByOrderId(long orderId);
	
	public List<Order> getUnfilledOrders();
	public int getNumberOfDrinksInQueue();

}
