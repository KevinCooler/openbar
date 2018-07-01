package openbardir.openbardir;

import org.apache.commons.dbcp2.BasicDataSource;

import jdbcdao.JDBCDrinkDAO;
import jdbcdao.JDBCPurchaseOrderDAO;
import menu.Menu;
import openbardir.openbardir.customer.Customer;
import openbardir.openbardir.customer.CustomerDAO;
import openbardir.openbardir.customer.JDBCCustomerDAO;


public class OpenbarCLI {
	
	private static final String MAIN_MENU_OPTION_CREATE_ACCOUNT = "Create New Account";
	private static final String MAIN_MENU_OPTION_LOG_IN = "Log In";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_CREATE_ACCOUNT, MAIN_MENU_OPTION_LOG_IN, MAIN_MENU_OPTION_EXIT};
	
	
	private Menu menu;
//	private DrinkDAO drinkDAO;
//	private PurchaseOrderDAO purchaseOrderDAO;
	private CustomerDAO customerDAO;
	
	public static void main(String[] args) {
		OpenbarCLI application = new OpenbarCLI();
		application.run();
	}
	
	public OpenbarCLI() {
		this.menu = new Menu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/openbar");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		customerDAO = new JDBCCustomerDAO(dataSource);
//		drinkDAO = new JDBCDrinkDAO(dataSource);
//		purchaseOrderDAO = new JDBCPurchaseOrderDAO(dataSource);
	}

	private void run() {

		menu.displayApplicationBanner();	
		while(true) {
			menu.printHeading("Main Menu");
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(choice.equals(MAIN_MENU_OPTION_CREATE_ACCOUNT)) {
				handleCreateAccount();
			} else if(choice.equals(MAIN_MENU_OPTION_LOG_IN)) {
				handleLogIn();
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
			}
		}
	}

	private void handleLogIn() {
		// TODO Auto-generated method stub
	}

	private void handleCreateAccount() {
		menu.printHeading("Create New Account");
		String[] newCustomerInfo = menu.getNewCustomerInfo();
		Customer newCustomer = new Customer();
		newCustomer.setEmail(newCustomerInfo[0]);
		newCustomer.setCreditCardNumber(newCustomerInfo[1]);
		newCustomer.setName(newCustomerInfo[2]);
		
		String email = customerDAO.createCustomerAccount(newCustomer);
		menu.displayTextToUser("Your new account has been successfully created.");
	}
	
	private void handleUpdateAccountInfo() {
		
	}
	
	
	
	
}
