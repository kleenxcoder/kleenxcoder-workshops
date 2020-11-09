package com.kleenxcoder.hibernate.SessionEventListener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kleenxcoder.hibernate.SessionEventListener.entity.User;
import com.kleenxcoder.hibernate.SessionEventListener.service.UserService;

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
	public Iterable<User> findAll() {
		return userService.findAllUser();
	}
	
	@GetMapping("/generate")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<User> generate() {
		return userService.generate();
	}
	
	@GetMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String delete(@PathVariable long id) {
		userService.deleteUser(id);
		return "deleted";
	}
	
	@GetMapping("/findByFirstName/{firstName}")
	@ResponseStatus(HttpStatus.OK)
	public List<User> delete(@PathVariable String firstName) {
		return userService.findByFirstName(firstName);
	}

}
