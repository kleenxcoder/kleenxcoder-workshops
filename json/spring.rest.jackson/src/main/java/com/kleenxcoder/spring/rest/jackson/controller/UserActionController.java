package com.kleenxcoder.spring.rest.jackson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.kleenxcoder.spring.rest.jackson.entity.UserAction;
import com.kleenxcoder.spring.rest.jackson.jsonfiler.CustomJsonView;
import com.kleenxcoder.spring.rest.jackson.service.UserActionService;

/**
 * @author kleenxcoder
 */

@RestController
@RequestMapping(value = "/api/kleenxcoder/useraction")
public class UserActionController {
	
	@Autowired
	UserActionService service;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(CustomJsonView.UserActionView.class)
	public Iterable<UserAction> findAll() {
		return service.findAll();
	}

}
