package openbardir.openbardir.order;

import java.util.List;


public interface OrderDAO {
	
	public Order getOrderByOrderId(long orderId);
	
	public List<Order> getAllOrders();
	public List<Order> getUnfilledOrders();
	public List<Order> getAllOrdersByEmail(String email);
	
	public long submitOrder(Order order);
	public double getCostofOrderByOrderId(long orderId);
	public int getNumberOfDrinksInQueue();
	
	public void updateOrder(Order order);
	
	

	

	

}
