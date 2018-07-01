package menu;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

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
}
