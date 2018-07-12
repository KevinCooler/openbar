package com.openbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String displayHomePage(@RequestParam String email, ModelMap map) {
		Customer customer = customerDAO.lookupCustomerAccountByEmail(email);
		map.addAttribute("customer",  customer);
		return "homePage";
	}

}
