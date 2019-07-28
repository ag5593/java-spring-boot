package com.general.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class Token extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 4153429079598706215L;

	private String token;

    public Token(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
