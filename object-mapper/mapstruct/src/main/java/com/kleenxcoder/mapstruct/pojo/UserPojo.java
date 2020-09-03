package com.kleenxcoder.mapstruct.pojo;

import com.kleenxcoder.mapstruct.entity.UserAction;

import lombok.Data;

/**
 * @author kleenxcoder
 */

@Data
public class UserPojo {
	
	private String firstName;
	
	private String lastName;
	
	private UserAction lastAction;

}
