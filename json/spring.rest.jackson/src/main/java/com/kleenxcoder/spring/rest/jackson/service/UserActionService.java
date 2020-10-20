package com.kleenxcoder.spring.rest.jackson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kleenxcoder.spring.rest.jackson.entity.UserAction;
import com.kleenxcoder.spring.rest.jackson.repository.UserActionRepository;

/**
 * @author kleenxcoder
 */

@Service
public class UserActionService {
	
	@Autowired
	UserActionRepository repository;
	
	public Iterable<UserAction> findAll() {
		return repository.findAll();
	}

}
