package com.openbar.model.dao;

import com.openbar.model.Customer;

public interface CustomerDAO {
	
	public String createCustomerAccount(Customer customer);
	
	public Customer lookupCustomerAccountByEmail(String email);
	
	public Customer updateCustomerAccount(Customer customer);

}
