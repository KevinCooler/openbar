package com.openbar.model;

public class Bar {
	
	private long barId;
	private String name;
	private String accountNumber;
	
	public long getBarId() {
		return barId;
	}
	public void setBarId(long barId) {
		this.barId = barId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (barId ^ (barId >>> 32));
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
		Bar other = (Bar) obj;
		if (barId != other.barId)
			return false;
		return true;
	}
	
	
	

}
