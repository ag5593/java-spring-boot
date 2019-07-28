package com.general.controller.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.general.security.TokenGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	TokenGenerator tokenGenerator;
	
	@GetMapping("{userId}")
	public String getProfileByUserId(@PathVariable("userId") String userId) {
		return tokenGenerator.generateToken(userId);
	}
}
