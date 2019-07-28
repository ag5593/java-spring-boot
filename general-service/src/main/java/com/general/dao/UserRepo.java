package com.general.dao;

import org.springframework.data.repository.CrudRepository;

import com.general.model.User;

public interface UserRepo extends CrudRepository<User, String> {

}
