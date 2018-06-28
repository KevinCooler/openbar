package openbardir.openbardir;

import org.apache.commons.dbcp2.BasicDataSource;

import jdbcdao.JDBCDrinkDAO;
import jdbcdao.JDBCPurchaseOrderDAO;
import menu.Menu;


public class OpenbarCLI {
	
	private Menu menu;
	private DrinkDAO drinkDAO;
	private PurchaseOrderDAO purchaseOrderDAO;
	
	public static void main(String[] args) {
		OpenbarCLI application = new OpenbarCLI();
//		application.run();
	}
	
	public OpenbarCLI() {
		this.menu = new Menu(System.in, System.out);
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/openbar");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		drinkDAO = new JDBCDrinkDAO(dataSource);
		purchaseOrderDAO = new JDBCPurchaseOrderDAO(dataSource);
	}

//	private void run() {
//		displayApplicationBanner();	
//		while(true) {
//			printHeading("Main Menu");
//			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
//			if(choice.equals(MAIN_MENU_OPTION_DEPARTMENTS)) {
//				handleDepartments();
//			} else if(choice.equals(MAIN_MENU_OPTION_EMPLOYEES)) {
//				handleEmployees();
//			} else if(choice.equals(MAIN_MENU_OPTION_PROJECTS)) {
//				handleProjects();
//			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
//				System.exit(0);
//			}
//		}
//	}

}
