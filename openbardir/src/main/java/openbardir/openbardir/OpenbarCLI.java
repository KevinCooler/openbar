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
import openbardir.openbardir.order.JDBCOrderDAO;
import openbardir.openbardir.order.Order;
import openbardir.openbardir.order.OrderDAO;


public class OpenbarCLI {
	
	private static final String MAIN_MENU_OPTION_CREATE_ACCOUNT = "Create New Account";
	private static final String MAIN_MENU_OPTION_LOG_IN = "Log In";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_LOG_IN, MAIN_MENU_OPTION_CREATE_ACCOUNT, MAIN_MENU_OPTION_EXIT};
	
	private static final String DRINK_MENU_OPTION__ALL_ORDERS = "Select from All Previous Orders";
	private static final String DRINK_MENU_OPTION_PREVIOUS_ORDERS = "Select from Your Previous Orders";
	private static final String DRINK_MENU_OPTION_ALL_AVAILABLE = "Select from All Available Drinks";
	private static final String DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO = "View Account Info";
	private static final String DRINK_MENU_OPTION_LOGOUT = "Log Out";
	private static final String[] DRINK_MENU_OPTIONS = {DRINK_MENU_OPTION__ALL_ORDERS, DRINK_MENU_OPTION_PREVIOUS_ORDERS, DRINK_MENU_OPTION_ALL_AVAILABLE, DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO, DRINK_MENU_OPTION_LOGOUT};
	
	private static final String UPDATE_ACCOUNT_MENU_OPTION_NAME = "Update Display Name";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER = "Update Credit Card Number";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_GO_BACK = "Go Back";
	private static final String[] UPDATE_ACCOUNT_MENU_OPTIONS = {UPDATE_ACCOUNT_MENU_OPTION_NAME, UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER, UPDATE_ACCOUNT_MENU_OPTION_GO_BACK};
	
	private static final String ORDER_MENU_OPTION_SUBMIT_ORDER = "Submit Order";
	private static final String ORDER_MENU_OPTION_CHANGE_QUANTITY = "Change Quantity";
	private static final String ORDER_MENU_OPTION_ADD_COMMENT = "Add Comment";
	private static final String ORDER_MENU_OPTION_GO_BACK = "Go Back";
	private static final String[] ORDER_MENU_OPTIONS = {ORDER_MENU_OPTION_SUBMIT_ORDER, ORDER_MENU_OPTION_CHANGE_QUANTITY, ORDER_MENU_OPTION_ADD_COMMENT, ORDER_MENU_OPTION_GO_BACK};
	
	private Menu menu;
	private DrinkDAO drinkDAO;
	private OrderDAO orderDAO;
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
		orderDAO = new JDBCOrderDAO(dataSource);
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
				menu.printHeading("All Previous Orders");
				handleDrinkSelection(drinkDAO.getDrinksOfAllOrders());
			} else if(choice.equals(DRINK_MENU_OPTION_PREVIOUS_ORDERS)) {
				menu.printHeading("Your Previous Orders");
				handleDrinkSelection(drinkDAO.getDrinksOfAllOrdersByEmail(customer.getEmail()));
			} else if(choice.equals(DRINK_MENU_OPTION_ALL_AVAILABLE)) {
				menu.printHeading("Available Drinks");
				handleDrinkSelection(drinkDAO.getAvailableDrinks());
//				handleSelectFromAllAvailable();
			} else if(choice.equals(DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO)) {
				customer = handleViewAccountInfo(customer);
			} else if(choice.equals(DRINK_MENU_OPTION_LOGOUT)) {
				menu.displayTextToUser("You have been logged out.");
				break;
			}
		}
	}
	
	private void handleDrinkSelection(List<Drink> drinks) {
		Drink selectedDrink = menu.getDrinkFromOptions(drinks);
		if (selectedDrink == null);
		else runPurchaseOrder(selectedDrink);
	}

	private void runPurchaseOrder(Drink selectedDrink) {
		Order order = new Order();
		order.setDrinkId(selectedDrink.getDrinkId());
		order.setEmail(customer.getEmail());
		order.setQuantity(1);
		order.setComment("<none>");
		while(true) {
			menu.displayOrderDetails(order, selectedDrink);
			String choice = (String)menu.getChoiceFromOptions(ORDER_MENU_OPTIONS);
			if(choice.equals(ORDER_MENU_OPTION_SUBMIT_ORDER)) {
				handleSubmitOrder(order);
				break;
			} else if(choice.equals(ORDER_MENU_OPTION_CHANGE_QUANTITY)) {
				order = handleChangeQuantity(order);
			} else if(choice.equals(ORDER_MENU_OPTION_ADD_COMMENT)) {
				order = handleAddComment(order);
			} else if(choice.equals(ORDER_MENU_OPTION_GO_BACK)) {
				break;
			}
		}
	}

	private void handleSubmitOrder(Order order) {
		long orderId = orderDAO.submitOrder(order);
		order.setOrderId(orderId);
		double cost = orderDAO.getCostofOrderByOrderId(order.getOrderId());
		menu.displayOrderConfirmation(order, cost, customer.getCreditCardNumber());
	}

	private Order handleAddComment(Order order) {
		String comment = menu.getCommentFromUser();
		order.setComment(comment);
		return order;
	}

	private Order handleChangeQuantity(Order order) {
		int quantity = menu.getDesiredQuantity();
		order.setQuantity(quantity);
		return order;
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
