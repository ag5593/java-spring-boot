package com.general.controller.profile;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.general.model.JwtUserDetails;



@RestController
@RequestMapping("/rest/general/profile")
public class ProfileController {
	
	@PermitAll
	@GetMapping()
	public String getProfileByUserId(@AuthenticationPrincipal final JwtUserDetails userDetails) {
		return "Profile of User "+userDetails.getUserName()+" and Role "+userDetails.getAuthorities().toString();
	}
	
	@RolesAllowed("admin")
	@GetMapping("/adminOnly")
	public String onlyAdminCanAccess(@AuthenticationPrincipal final JwtUserDetails userDetails) {
		return "I am "+userDetails.getUserName()+" and my role is admin";
		
	}
	
	@RolesAllowed({"admin", "pm"})
	@GetMapping("/pmOnly")
	public String adminOrPMCanAccess(@AuthenticationPrincipal final JwtUserDetails userDetails) {
		return "I am "+userDetails.getUserName()+" and my role is "+userDetails.getAuthorities().toString();
	}

}
