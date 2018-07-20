package com.openbar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.openbar.model.Bar;
import com.openbar.model.Customer;
import com.openbar.model.Drink;
import com.openbar.model.DrinkOrder;
import com.openbar.model.Order;
import com.openbar.model.dao.BarDAO;
import com.openbar.model.dao.CustomerDAO;
import com.openbar.model.dao.DrinkDAO;
import com.openbar.model.dao.DrinkOrderDAO;

@Controller
@SessionAttributes("drinkOrder")
public class OpenController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private BarDAO barDAO;
	
	@Autowired
	private DrinkDAO drinkDAO;
	
	@Autowired
	private DrinkOrderDAO drinkOrderDAO;
	
	@RequestMapping("/")
	public String displayLogIn(ModelMap map) {
		map.addAttribute("drinkOrder", new DrinkOrder());
		return "logIn";
	}
	
	@RequestMapping(path="/doLogIn", method=RequestMethod.POST)
	public String doLogIn(@RequestParam String email, ModelMap map) {
		DrinkOrder drinkOrder = (DrinkOrder)map.get("drinkOrder");
		drinkOrder.setEmail(email);
		drinkOrder.setCustomerName(customerDAO.lookupCustomerAccountByEmail(email).getName());
		return "redirect:/homePage";
	}
	
	@RequestMapping(path="/doCreateAccount", method=RequestMethod.POST)
	public String doCreateAccount(@RequestParam String email,
									@RequestParam String name,
									@RequestParam String creditCardNumber) {
		Customer customer = new Customer();
		customer.setCreditCardNumber(creditCardNumber);
		customer.setEmail(email);
		customer.setName(name);
		customerDAO.createCustomerAccount(customer);
		return "redirect:/";
	}
	
	@RequestMapping("/homePage")
	public String displayHomePage(ModelMap map) {
		map.addAttribute("bars", barDAO.getListOfAllBars());
		return "homePage";
	}
	
	@RequestMapping("/barPage")
	public String displayBarPage(ModelMap map, @RequestParam (required=false) Long barId) {
		DrinkOrder drinkOrder = (DrinkOrder)map.get("drinkOrder");
		if(barId != null) {
			drinkOrder.setBarId(barId);
		}
		map.addAttribute("drinkOrders", drinkOrderDAO.getAvailableCurrentDrinkOrdersAtBar(drinkOrder.getBarId()));
		map.addAttribute("bar", barDAO.getBarByBarId(drinkOrder.getBarId()));
		return "barPage";
	}
	
	@RequestMapping("/barHistory")
	public String displayBarHistoryPage(ModelMap map) {
		DrinkOrder drinkOrder = (DrinkOrder)map.get("drinkOrder");
		map.addAttribute("drinkOrders", drinkOrderDAO.getAvailableDrinkOrdersOfCustomerAtBar(drinkOrder.getBarId(), drinkOrder.getEmail()));
		map.addAttribute("bar", barDAO.getBarByBarId(drinkOrder.getBarId()));
		return "barHistory";
	}
	
	@RequestMapping("/barDrinkSearch")
	public String displayDrinkSearchPage(ModelMap map) {
		DrinkOrder drinkOrder = (DrinkOrder)map.get("drinkOrder");
		map.addAttribute("drinkOrders", drinkOrderDAO.getAvailableDrinkOrdersAtBar(drinkOrder.getBarId()));
		map.addAttribute("bar", barDAO.getBarByBarId(drinkOrder.getBarId()));
		return "drinkSearch";
	}
	
	@RequestMapping("/orderPage")
	public String displayOrderPage(ModelMap map, @RequestParam long drinkId, double price) {
		DrinkOrder drinkOrder = (DrinkOrder)map.get("drinkOrder");
		Drink drink = drinkDAO.getDrinkByDrinkId(drinkId);
		drinkOrder.setDrinkId(drinkId);
		drinkOrder.setPrice(price);
		drinkOrder.setCategory(drink.getCategory());
		drinkOrder.setDrinkName(drink.getName());
		drinkOrder.setType(drink.getType());
		drinkOrder.setBrand(drink.getBrand());
		map.addAttribute("bar", barDAO.getBarByBarId(drinkOrder.getBarId()));
		return "orderPage";
	}
	
	
	
	
	
	
}
