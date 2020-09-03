package com.kleenxcoder.mapstruct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kleenxcoder.mapstruct.entity.User;
import com.kleenxcoder.mapstruct.pojo.UserPojo;
import com.kleenxcoder.mapstruct.service.UserService;

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
	public Iterable<UserPojo> findAll() {
		return userService.findAllUserPojo();
	}
	
	@GetMapping("/generate")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<User> generate() {
		return userService.generate();
	}
	

}
