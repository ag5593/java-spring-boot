package com.general.security;

import org.springframework.stereotype.Component;

import com.general.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenValidator {
	private String secret = "skey";
	
	public User validate(String token) {
		User user = null;
		 Claims body = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody();
		 user = new User();
		 user.setUserId(body.getSubject());
		return user;
	}
}
