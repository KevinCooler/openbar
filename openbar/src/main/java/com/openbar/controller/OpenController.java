package com.openbar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenController {
	
	@RequestMapping("/")
	public String displayHomePage() {
		return "homePage";
	}

}
