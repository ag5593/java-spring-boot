package com.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/report")
public class ReportController {
	
	
	@GetMapping("/get")
	public String getReport() {
		return "GET Report";
	}
	
	

}
