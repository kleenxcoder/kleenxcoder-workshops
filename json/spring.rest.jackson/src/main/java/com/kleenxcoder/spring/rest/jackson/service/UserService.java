package com.kleenxcoder.spring.rest.jackson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kleenxcoder.spring.rest.jackson.entity.User;
import com.kleenxcoder.spring.rest.jackson.entity.UserAction;
import com.kleenxcoder.spring.rest.jackson.repository.UserRepository;

/**
 * @author kleenxcoder
 */

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public Iterable<User> generate() {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setFirstName("first " + i);
			user.setLastName("last " + i);
			for (int j = 0; j < 5; j++) {
				UserAction userAction = new UserAction();
				userAction.setAction("action");
				userAction.setUser(user);
				user.getUserAction().add(userAction);
			}
			users.add(user);
		}
		
		return userRepository.saveAll(users);
	}


	public Iterable<User> findAllUser() {
		return userRepository.findAll();
	}

}
