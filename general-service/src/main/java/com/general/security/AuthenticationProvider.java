package com.general.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.general.model.JwtUserDetails;
import com.general.model.Token;
import com.general.model.User;
import com.general.services.UserService;



	
@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private TokenValidator validator;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		Token token = (Token) authentication;
        String tokenValue = token.getToken();

        User user = validator.validate(tokenValue);

        if (user == null) {
            throw new RuntimeException("Token is incorrect");
        }
        
        User userFromDB = userService.findUserById(user.getUserId());
        
        if (userFromDB == null) {
            throw new RuntimeException("User is not registered");
        }
        
        
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(userFromDB.getRole());
        
        return new JwtUserDetails(userFromDB.getUserId(), 
        		tokenValue,
        		grantedAuthorities);
		
	}
	
	@Override
    public boolean supports(Class<?> aClass) {
        return (Token.class.isAssignableFrom(aClass));
	}

}
