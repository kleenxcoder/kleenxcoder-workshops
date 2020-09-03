package com.kleenxcoder.mapstruct.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kleenxcoder.mapstruct.entity.User;
import com.kleenxcoder.mapstruct.entity.UserAction;
import com.kleenxcoder.mapstruct.mapper.User2UserPojoMapper;
import com.kleenxcoder.mapstruct.pojo.UserPojo;
import com.kleenxcoder.mapstruct.repository.UserRepository;

/**
 * @author kleenxcoder
 */

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    User2UserPojoMapper mapper;

	public Iterable<UserPojo> findAllUserPojo() {
		List<UserPojo> userPojo = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			userPojo.add(mapper.map(user));
		}
		return userPojo;
	}

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

}
