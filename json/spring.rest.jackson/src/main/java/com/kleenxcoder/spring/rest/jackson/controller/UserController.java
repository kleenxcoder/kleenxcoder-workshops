package com.kleenxcoder.spring.rest.jackson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.kleenxcoder.spring.rest.jackson.entity.User;
import com.kleenxcoder.spring.rest.jackson.jsonfiler.CustomJsonView;
import com.kleenxcoder.spring.rest.jackson.service.UserService;

/**
 * @author kleenxcoder
 */

@RestController
@RequestMapping(value = "/api/kleenxcoder/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(CustomJsonView.UserView.class)
	public Iterable<User> findAll() {
		return userService.findAllUser();
	}
	
	@GetMapping("/generate")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(CustomJsonView.UserView.class)
	public Iterable<User> generate() {
		return userService.generate();
	}

}
