package com.javaetraining.spring.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaetraining.spring.boot.dao.UserDao;
import com.javaetraining.spring.boot.model.User;
import com.javaetraining.spring.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUserById(long id) {
		userDao.deleteUserById(id);

	}

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void deleteAllUsers() {
		userDao.deleteAllUsers();
	}

	@Override
	public boolean isUserExist(User user) {
		return userDao.isUserExist(user);
	}

}
