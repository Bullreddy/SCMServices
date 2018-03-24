package com.reddys.IT.ITServices;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/hello")
	public String execute(){
		return "Hello Spring Boot";
	}

}
