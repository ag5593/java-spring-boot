package com.general.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {
	
	public String generateToken(String userName) {
		Claims claims = Jwts.claims().setSubject(userName);
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, "skey")
				.setExpiration(new Date(System.currentTimeMillis() + 3000000))
				.compact();
	}

}
