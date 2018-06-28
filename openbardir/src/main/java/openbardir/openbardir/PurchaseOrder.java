package openbardir.openbardir;

import java.time.LocalDateTime;

public class PurchaseOrder {
	
	private Long purchaseOrderId;
	private Long drinkId;
	private LocalDateTime dateTime;
	private String email;
	private boolean isComplete;
	private int quantity;
	
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public Long getDrinkId() {
		return drinkId;
	}
	public void setDrinkId(Long drinkId) {
		this.drinkId = drinkId;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
