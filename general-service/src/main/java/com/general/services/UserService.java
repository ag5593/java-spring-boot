package com.general.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.general.dao.UserRepo;
import com.general.model.User;

@Component
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public User findUserById(String id) {
		User user = userRepo.findById(id).orElse(null);
		return user;
	}
}
