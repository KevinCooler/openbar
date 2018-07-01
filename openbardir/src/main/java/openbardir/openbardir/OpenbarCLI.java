package openbardir.openbardir;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource; 

import menu.Menu;
import openbardir.openbardir.customer.Customer;
import openbardir.openbardir.customer.CustomerDAO;
import openbardir.openbardir.customer.JDBCCustomerDAO;
import openbardir.openbardir.drink.Drink;
import openbardir.openbardir.drink.DrinkDAO;
import openbardir.openbardir.drink.JDBCDrinkDAO;


public class OpenbarCLI {
	
	private static final String MAIN_MENU_OPTION_CREATE_ACCOUNT = "Create New Account";
	private static final String MAIN_MENU_OPTION_LOG_IN = "Log In";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_LOG_IN, MAIN_MENU_OPTION_CREATE_ACCOUNT, MAIN_MENU_OPTION_EXIT};
	
	private static final String DRINK_MENU_OPTION__ALL_ORDERS = "Select from All Orders";
	private static final String DRINK_MENU_OPTION_PREVIOUS_ORDERS = "Select from Previous Orders";
	private static final String DRINK_MENU_OPTION_ALL_AVAILABLE = "Select from All Available";
	private static final String DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO = "View Account Info";
	private static final String DRINK_MENU_OPTION_LOGOUT = "Log Out";
	private static final String[] DRINK_MENU_OPTIONS = {DRINK_MENU_OPTION__ALL_ORDERS, DRINK_MENU_OPTION_PREVIOUS_ORDERS, DRINK_MENU_OPTION_ALL_AVAILABLE, DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO, DRINK_MENU_OPTION_LOGOUT};
	
	private static final String UPDATE_ACCOUNT_MENU_OPTION_NAME = "Update Display Name";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER = "Update Credit Card Number";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_GO_BACK = "Go Back";
	private static final String[] UPDATE_ACCOUNT_MENU_OPTIONS = {UPDATE_ACCOUNT_MENU_OPTION_NAME, UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER, UPDATE_ACCOUNT_MENU_OPTION_GO_BACK};
	
	private Menu menu;
	private DrinkDAO drinkDAO;
//	private PurchaseOrderDAO purchaseOrderDAO;
	private CustomerDAO customerDAO;
	private Customer customer;
	
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
		drinkDAO = new JDBCDrinkDAO(dataSource);
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
				customer = handleLogIn();
				runDrinkOrder(customer);
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				menu.displayTextToUser("Thanks for using Openbar. Cheers!");
				System.exit(0);
			}
		}
	}

	private void runDrinkOrder(Customer customer) {
		menu.displayApplicationBanner();
		while(true) {
			menu.printHeading("Drink Menu");
			String choice = (String)menu.getChoiceFromOptions(DRINK_MENU_OPTIONS);
			if(choice.equals(DRINK_MENU_OPTION__ALL_ORDERS)) {
//				handleSelectFromAllOrders();
			} else if(choice.equals(DRINK_MENU_OPTION_PREVIOUS_ORDERS)) {
//				handleSelectFromPreviousOrders();
			} else if(choice.equals(DRINK_MENU_OPTION_ALL_AVAILABLE)) {
				handleSelectFromAllAvailable();
			} else if(choice.equals(DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO)) {
				customer = handleViewAccountInfo(customer);
			} else if(choice.equals(DRINK_MENU_OPTION_LOGOUT)) {
				menu.displayTextToUser("You have been logged out.");
				break;
			}
		}
	}

	private void handleSelectFromAllAvailable() {
		menu.printHeading("Available Drinks");
		List<Drink> availableDrinks = drinkDAO.getAvailableDrinks();
		Drink selectedDrink = menu.getDrinkFromOptions(availableDrinks);
		if (selectedDrink == null);
		else runPurchaseOrder(selectedDrink);
	}

	private void runPurchaseOrder(Drink selectedDrink) {
		// TODO Auto-generated method stub
		
	}

	private Customer handleLogIn() {
		menu.printHeading("Log in to Existing Account");
		String email = menu.getCustomerEmail();
		Customer customer = customerDAO.lookupCustomerAccountByEmail(email);
		menu.displayTextToUser("Welcome back, " + customer.getName() + ". Please drink responsibly.\n");
		return customer;
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
	
	private Customer handleViewAccountInfo(Customer customer) {
		while(true) {
			menu.printHeading("Account Information");
			menu.displayAccountInfo(customer);
			String choice = (String)menu.getChoiceFromOptions(UPDATE_ACCOUNT_MENU_OPTIONS);
			if(choice.equals(UPDATE_ACCOUNT_MENU_OPTION_NAME)) {
				customer = handleUpdateCustomerName(customer);
			} else if(choice.equals(UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER)) {
				customer = handleUpdateCustomerCreditCardNumber(customer);
			} else if(choice.equals(UPDATE_ACCOUNT_MENU_OPTION_GO_BACK)) {
				break;
			}
		}
		return customer;
	}

	private Customer handleUpdateCustomerCreditCardNumber(Customer customer2) {
		String newCreditCardNumber = menu.getNewCreditCardNumberFromUser();
		customer.setCreditCardNumber(newCreditCardNumber);
		customer = customerDAO.updateCustomerAccount(customer);
		return customer;
	}

	private Customer handleUpdateCustomerName(Customer customer) {
		String newName = menu.getNewNameFromUser();
		customer.setName(newName);
		customer = customerDAO.updateCustomerAccount(customer);
		return customer;
	}

	
	
	
}
