package com.general.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.general.model.Token;


public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	protected AuthenticationFilter(String requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String token= request.getHeader("Authorization");
		
		if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Token is missing");
        }

        String authenticationToken = token.substring(7);
        
        Token authToken = new Token(authenticationToken);
        return getAuthenticationManager().authenticate(authToken);
	}
	
	@Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}
