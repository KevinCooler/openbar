package com.openbar.model;

import java.sql.Timestamp;

public class Order {
	
	private Long orderId;
	private Long drinkId;
	private Long barId;
	private Timestamp dateTime;
	private String email;
	private String status;
	private int quantity;
	private String comment;
	
	public Long getBarId() {
		return barId;
	}
	public void setBarId(Long barId) {
		this.barId = barId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getDrinkId() {
		return drinkId;
	}
	public void setDrinkId(Long drinkId) {
		this.drinkId = drinkId;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", drinkId=" + drinkId + ", dateTime=" + dateTime + ", quantity="
				+ quantity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
