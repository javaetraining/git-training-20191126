package com.javaetraining.spring.boot.dao;

import java.util.List;

import com.javaetraining.spring.boot.model.User;

public interface UserDao {

	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
}
