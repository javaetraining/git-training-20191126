package com.javaetraining.spring.boot.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.javaetraining.spring.boot.dao.UserDao;
import com.javaetraining.spring.boot.model.User;

@Component
public class UserDaoImpl implements UserDao {

	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Sam", new Date(), "sam@gmial.com"));
		users.add(new User(counter.incrementAndGet(), "Tom", new Date(), "tom@gmial.com"));
		users.add(new User(counter.incrementAndGet(), "Jerome", new Date(), "jer@gmial.com"));
		users.add(new User(counter.incrementAndGet(), "Silvia", new Date(), "sil@gmial.com"));
		return users;
	}

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	@Override
	public void deleteUserById(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}

	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void deleteAllUsers() {
		users.clear();

	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}

}
