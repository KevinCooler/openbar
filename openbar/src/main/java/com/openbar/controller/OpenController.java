package com.openbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.openbar.model.Customer;
import com.openbar.model.dao.CustomerDAO;

@Controller
@SessionAttributes("customer")
public class OpenController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping("/")
	public String displayLogIn() {
		return "logIn";
	}
	
	@RequestMapping("/homePage")
	public String displayHomePage() {
		return "homePage";
	}
	
	@RequestMapping(path="/doLogIn", method=RequestMethod.POST)
	public String doLogIn(@RequestParam String email, ModelMap map) {
		Customer customer = customerDAO.lookupCustomerAccountByEmail(email);
		map.addAttribute("customer",  customer);
		return "redirect:/homePage";
	}
	
	@RequestMapping(path="/doCreateAccount", method=RequestMethod.POST)
	public String doCreateAccount(ModelMap map, @RequestParam String email,
												@RequestParam String name,
												@RequestParam String creditCardNumber) {
		Customer customer = new Customer();
		customer.setCreditCardNumber(creditCardNumber);
		customer.setEmail(email);
		customer.setName(name);
		customerDAO.createCustomerAccount(customer);
		map.addAttribute("customer",  customer);
		return "redirect:/";
	}

}
