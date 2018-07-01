package openbardir.openbardir.customer;

public interface CustomerDAO {
	
	public String createCustomerAccount(Customer customer);
	
	public Customer lookupCustomerAccountByEmail(String email);
	
	public Customer updateCustomerAccount(Customer customer);

}
