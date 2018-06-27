package openbardir.openbardir;

public class Drink {
	
	private Long drinkId;
	private String name;
	private Long purchaseOrderId;
	private int quantity;
	private String comment;
	
	public Long getDrinkId() {
		return drinkId;
	}
	public void setDrinkId(Long drinkId) {
		this.drinkId = drinkId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
