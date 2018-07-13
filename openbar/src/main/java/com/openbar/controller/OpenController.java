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
import com.openbar.model.dao.BarDAO;
import com.openbar.model.dao.CustomerDAO;

@Controller
@SessionAttributes({"customer", "bar"})
public class OpenController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private BarDAO barDAO;
	
	@RequestMapping("/")
	public String displayLogIn() {
		return "logIn";
	}
	
	@RequestMapping("/homePage")
	public String displayHomePage(ModelMap map) {
		map.addAttribute("bars", barDAO.getListOfAllBars());
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
	
	@RequestMapping("/barPage")
	public String displayBarPage(ModelMap map, @RequestParam long barId) {
		Bar bar = new Bar();
		List<Bar> bars = barDAO.getListOfAllBars();
		for(Bar each: bars) {
			if(each.getBarId() == barId) bar = each;
		}
		map.addAttribute("bar", bar);
		return "barPage";
	}

}
