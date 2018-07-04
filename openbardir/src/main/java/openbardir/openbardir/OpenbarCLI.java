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
import openbardir.openbardir.employee.Employee;
import openbardir.openbardir.employee.EmployeeDAO;
import openbardir.openbardir.employee.JDBCEmployeeDAO;
import openbardir.openbardir.order.JDBCOrderDAO;
import openbardir.openbardir.order.Order;
import openbardir.openbardir.order.OrderDAO;


public class OpenbarCLI {
	
	private static final String MAIN_MENU_OPTION_CREATE_ACCOUNT = "Create New Account";
	private static final String MAIN_MENU_OPTION_LOG_IN = "Log In";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_EMPLOYEE_LOG_IN = "Employee Log In";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_LOG_IN, MAIN_MENU_OPTION_CREATE_ACCOUNT, MAIN_MENU_OPTION_EMPLOYEE_LOG_IN, MAIN_MENU_OPTION_EXIT};
	
	private static final String DRINK_MENU_OPTION__ALL_ORDERS = "Select from All Previous Orders";
	private static final String DRINK_MENU_OPTION_PREVIOUS_ORDERS = "Select from Your Previous Orders";
	private static final String DRINK_MENU_OPTION_ALL_AVAILABLE = "Select from All Available Drinks";
	private static final String DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO = "View Account Info";
	private static final String DRINK_MENU_OPTION_LOGOUT = "Log Out";
	private static final String[] DRINK_MENU_OPTIONS = {DRINK_MENU_OPTION__ALL_ORDERS, DRINK_MENU_OPTION_PREVIOUS_ORDERS, DRINK_MENU_OPTION_ALL_AVAILABLE, DRINK_MENU_OPTION_VIEW_ACCOUNT_INFO, DRINK_MENU_OPTION_LOGOUT};
	
	private static final String UPDATE_ACCOUNT_MENU_OPTION_NAME = "Update Display Name";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER = "Update Credit Card Number";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_VIEW_ORDER_HISTORY = "View Order History";
	private static final String UPDATE_ACCOUNT_MENU_OPTION_GO_BACK = "Go Back";
	private static final String[] UPDATE_ACCOUNT_MENU_OPTIONS = {UPDATE_ACCOUNT_MENU_OPTION_VIEW_ORDER_HISTORY, UPDATE_ACCOUNT_MENU_OPTION_NAME, UPDATE_ACCOUNT_MENU_OPTION_CREDIT_CARD_NUMBER, UPDATE_ACCOUNT_MENU_OPTION_GO_BACK};
	
	private static final String ORDER_MENU_OPTION_SUBMIT_ORDER = "Submit Order";
	private static final String ORDER_MENU_OPTION_CHANGE_QUANTITY = "Change Quantity";
	private static final String ORDER_MENU_OPTION_ADD_COMMENT = "Add Comment";
	private static final String ORDER_MENU_OPTION_GO_BACK = "Go Back";
	private static final String[] ORDER_MENU_OPTIONS = {ORDER_MENU_OPTION_SUBMIT_ORDER, ORDER_MENU_OPTION_CHANGE_QUANTITY, ORDER_MENU_OPTION_ADD_COMMENT, ORDER_MENU_OPTION_GO_BACK};
	
	private static final String EMPLOYEE_MENU_OPTION_SELECT_ORDER = "Select Order";
	private static final String EMPLOYEE_MENU_OPTION_MARK_DRINK_UNAVAILABLE = "Mark drink as unavaiable";
	private static final String EMPLOYEE_MENU_OPTION_LOG_OUT = "Log out";
	private static final String[] EMPLOYEE_MENU_OPTIONS = {EMPLOYEE_MENU_OPTION_SELECT_ORDER, EMPLOYEE_MENU_OPTION_MARK_DRINK_UNAVAILABLE, EMPLOYEE_MENU_OPTION_LOG_OUT};

	private static final int PROCESS_SECONDS_PER_DRINK = 20;
	
	private Menu menu;
	private DrinkDAO drinkDAO;
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private Customer customer;
	private Employee employee;
	
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
		
		employeeDAO = new JDBCEmployeeDAO(dataSource);
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
			} else if(choice.equals(MAIN_MENU_OPTION_EMPLOYEE_LOG_IN)) {
				employee = handleEmployeeLogIn();
				if (employee == null);
				else runEmployeeMenu();
			} else if(choice.equals(MAIN_MENU_OPTION_LOG_IN)) {
				customer = handleLogIn();
				if (customer == null);
				else runDrinkOrder(customer);
			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				menu.displayTextToUser("Thanks for using Openbar. Cheers!");
				System.exit(0);
			}
		}
	}

	private void runEmployeeMenu() {
		menu.displayApplicationBanner();
		while(true) {
			
			createEmloyeeOrderView();
			String choice = (String)menu.getChoiceFromOptions(EMPLOYEE_MENU_OPTIONS);
			if(choice.equals(EMPLOYEE_MENU_OPTION_SELECT_ORDER)) {
//				handleSelectOrder();
			} else if(choice.equals(EMPLOYEE_MENU_OPTION_MARK_DRINK_UNAVAILABLE)) {
//				handleMarkDrinkUnavailable();
			} else if(choice.equals(EMPLOYEE_MENU_OPTION_LOG_OUT)) {
				break;
			}
		}
		
	}

	private void createEmloyeeOrderView() {
		List<Order> orders = orderDAO.getAllOrders();
		for (Order order: orders) {
			Customer orderCustomer = customerDAO.lookupCustomerAccountByEmail(order.getEmail());
			Drink orderDrink = drinkDAO.getDrinkByDrinkId(order.getDrinkId());
		}
		
	}

	private void runDrinkOrder(Customer customer) {
		menu.displayApplicationBanner();
		while(true) {
			menu.printHeading("Drink Menu");
			displayQueueInfo();
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
		displayQueueInfo();
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
		if (customer == null) {
			menu.displayTextToUser("That email address could not be found. Please try again.\n");
		}
		else menu.displayTextToUser("Welcome back, " + customer.getName() + ". Please drink responsibly.\n");
		return customer;
	}
	private Employee handleEmployeeLogIn() {
		menu.printHeading("Employee Log In");
		Long employeeId = menu.getEmployeeId();
		this.employee = employeeDAO.lookupEmpoyeeByEmployeeId(employeeId);
		if (employee == null) {
			menu.displayTextToUser("Employee Id not found. Please try again.\n");
		}
		else menu.displayTextToUser("Welcome back, " + employee.getFirstName() + ". Have a great day!\n");
		return employee;
	}

	private void handleCreateAccount() {
		menu.printHeading("Create New Account");
		String[] newCustomerInfo = menu.getNewCustomerInfo();
		Customer newCustomer = new Customer();
		newCustomer.setEmail(newCustomerInfo[0]);
		newCustomer.setCreditCardNumber(newCustomerInfo[1]);
		newCustomer.setName(newCustomerInfo[2]);
		
		customerDAO.createCustomerAccount(newCustomer);
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
			} else if(choice.equals(UPDATE_ACCOUNT_MENU_OPTION_VIEW_ORDER_HISTORY)) {
				handleViewOrderHistory();
			} else if(choice.equals(UPDATE_ACCOUNT_MENU_OPTION_GO_BACK)) {
				break;
			}
		}
		return customer;
	}

	private void handleViewOrderHistory() {
		List<Order> orders = orderDAO.getAllOrdersByEmail(customer.getEmail());
		menu.displayCustomerOrders(orders);
	}

	private Customer handleUpdateCustomerCreditCardNumber(Customer customer) {
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
	
	private void displayQueueInfo() {
		int orderQueueCount = orderDAO.getUnfilledOrders().size();
		int queueSeconds = PROCESS_SECONDS_PER_DRINK * orderDAO.getNumberOfDrinksInQueue();
		int minutes = queueSeconds / 60;
		menu.displayQueueInfo(orderQueueCount, minutes);
	}
}
