package menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import openbardir.openbardir.customer.Customer;
import openbardir.openbardir.drink.Drink;
import openbardir.openbardir.order.Order;

public class Menu {
	
	public PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
	

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	public void displayApplicationBanner() {
		out.println("**************************************");
		out.println("*  / \\  |\\ |-- |\\  |    |>   /\\   |\\ *");
		out.println("* |   | |/ |-- | \\ |    |\\  /__\\  |/ *");
		out.println("*  \\ /  |  |__ |  \\|    |/ /    \\ |\\ *");
		out.println("**************************************");
		out.flush();
	}
	
	public void printHeading(String heading) {
		out.println("\n" + heading);
		for (int i = 0; i < heading.length(); i++) {
			out.print("-");
		}
		out.println();
		out.flush();
	}


	public void displayTextToUser(String string) {
		out.println(string);
		out.flush();
	}

	public String[] getNewCustomerInfo() {
		String[] info = new String[3];
		
		out.print("\nEnter email address >>> ");
		out.flush();
		info[0] = in.nextLine();
		out.print("Enter credit card number >>> ");
		out.flush();
		info[1] = in.nextLine();
		out.print("Enter display name >>> ");
		out.flush();
		info[2] = in.nextLine();
		
		return info;
	}

	public String getCustomerEmail() {
		out.print("\nEnter email address >>> ");
		out.flush();
		String email = in.nextLine();
		return email;
	}

	public String getNewNameFromUser() {
		out.print("Enter new display name >>> ");
		out.flush();
		return in.nextLine();
	}

	public String getNewCreditCardNumberFromUser() {
		out.print("Enter new credit card number >>> ");
		out.flush();
		return in.nextLine();
	}

	public void displayAccountInfo(Customer customer) {
		printHeading("Account Information");
		out.println("Email Address:      " + customer.getEmail());
		out.println("Display Name:       " + customer.getName());
		out.println("Credit Card Number: ************" + customer.getCreditCardNumber().substring(customer.getCreditCardNumber().length() - 4));
	}


	public Drink getDrinkFromOptions(List<Drink> drinks) {
		displayDrinkOptions(drinks);
		Drink drink = getDrinkSelection(drinks);
		return drink;
	}

	private Drink getDrinkSelection(List<Drink> drinks) {
		long drinkId = Long.parseLong(in.nextLine());
		Drink drink = null;
		
		for (Drink each: drinks) {
			if (each.getDrinkId() == drinkId) {
				drink = each;
				
			}
		}
		return drink;
	}

	private void displayDrinkOptions(List<Drink> drinks) {
		out.println("Drink Id   Price    Name");
		for (Drink drink: drinks) {
			out.printf("%-10d $%-7.2f %s\n", drink.getDrinkId(), drink.getPrice(), drink.getName());
		}
		out.print("\nEnter Drink Id to order or (0) to go back >>> ");
		out.flush();
	}


	public void displayOrderDetails(Order order, Drink drink) {
		printHeading("Order Details");
		out.println("Your current order is [" + order.getQuantity() + "] " + drink.getName());
		out.println("Comment: " + order.getComment());
		out.flush();
	}

	public String getCommentFromUser() {
		out.print("\nEnter comment: >>> ");
		out.flush();
		return in.nextLine();
	}

	public int getDesiredQuantity() {
		out.print("\nEnter quantity: >>> ");
		out.flush();
		return Integer.parseInt(in.nextLine());
	}


	public void displayOrderConfirmation(Order order, double cost, String creditCardNumber) {
		out.println("\nYour order has been submitted.");
		out.printf("$%3.2f has been charged to credit card ************%s\n"  , cost, creditCardNumber.substring(creditCardNumber.length() - 4));
		out.println("Confirmation #" + order.getOrderId() + "\n");
	}


	public void displayCustomerOrders(List<Order> orders) {
		printHeading("Previous Orders");
		for (Order order: orders) out.println(order.toString());
	}


	public void displayQueueInfo(int orderQueueCount, int minutes) {
		out.println("There are [" + orderQueueCount + "] orders in the Queue");	
		out.println("Estimated wait time: ["+minutes+"] minutes.");
	}


	public Long getEmployeeId() {
		out.print("\nEnter Employee Id: >>> ");
		out.flush();
		return Long.parseLong(in.nextLine());
	}


	public void displayEmloyeeOrderView(Order order, Drink drink, Customer customer, String status) {
		// need to display:
		// Order Id         Time     Status          Category        Quantity     Drink	           Comment	           Customer Name
		String comment = "";
		if(order.getComment() != null && !order.getComment().equals("<none>")) comment = order.getComment();
		
		String drinkName = drink.getName();
		if (!drink.isAvailable()) drinkName = "*" + drinkName;
		
		SimpleDateFormat format = new SimpleDateFormat("MMM dd HH:mm");
		out.printf("%-5d %-17s %-13s %-13s %-6d %-35s %30s\n", 
				order.getOrderId(), format.format(order.getDateTime()), status, drink.getCategory(), order.getQuantity(), drinkName, customer.getName());
		out.println("                                                           " + comment);
		out.println("-----------------------------------------------------------------------------------------------------------------------------");
	}


	public long getOrderId() {
		out.print("\nEnter Order Id: >>> ");
		out.flush();
		return Long.parseLong(in.nextLine());
	}

		


}
