package com.kleenxcoder.hibernate.SessionEventListener.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kleenxcoder.hibernate.SessionEventListener.entity.User;
import com.kleenxcoder.hibernate.SessionEventListener.repository.UserRepository;

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
			users.add(user);
		}
		
		return userRepository.saveAll(users);
	}


	public Iterable<User> findAllUser() {
		return userRepository.findAll();
	}
	
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}


	public List<User> findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

}
