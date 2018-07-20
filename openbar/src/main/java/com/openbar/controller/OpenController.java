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
import com.openbar.model.Order;
import com.openbar.model.dao.BarDAO;
import com.openbar.model.dao.CustomerDAO;
import com.openbar.model.dao.DrinkOrderDAO;

@Controller
@SessionAttributes({"customer", "bar", "Order"})
public class OpenController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private BarDAO barDAO;
	
	@Autowired
	private DrinkOrderDAO drinkOrderDAO;
	
	@RequestMapping("/")
	public String displayLogIn(ModelMap map) {
		map.addAttribute("order", new Order());
		return "logIn";
	}
	
	@RequestMapping(path="/doLogIn", method=RequestMethod.POST)
	public String doLogIn(@RequestParam String email, ModelMap map) {
		Order order = (Order)map.get("order");
		order.setEmail(email);
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
	public String displayBarPage(ModelMap map, @RequestParam long barId) {
		Bar bar = barDAO.getBarByBarId(barId);
		Order order = (Order)map.get("order");
		order.setBarId(barId);
		map.addAttribute("drinkOrders", drinkOrderDAO.getDrinkOrdersByBarId(barId));
		map.addAttribute("bar", bar);
		return "barPage";
	}
	
	@RequestMapping("/barHistory")
	public String displayBarHistoryPage(ModelMap map) {
		Order order = (Order)map.get("order");
		Bar bar = barDAO.getBarByBarId(order.getBarId());
		map.addAttribute("drinkOrders", drinkOrderDAO.getDrinkOrdersOfCustomerAtBar(order.getBarId(), order.getEmail()));
		map.addAttribute("bar", bar);
	}
	
	
	
	
	
	
}
