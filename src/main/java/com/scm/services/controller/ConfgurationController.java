package com.scm.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scm.services.serviceinf.ConfigurationService;

@RestController
public class ConfgurationController {
	
	@Autowired
	private ConfigurationService service;
	
	@RequestMapping("/getConfigurations")
	public List getConfigurations(){
		return service.getConfiguration(null);
	}
	

}
