package com.kleenxcoder.spring.rest.jackson.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.kleenxcoder.spring.rest.jackson.entity.comparator.UserActionComparator;
import com.kleenxcoder.spring.rest.jackson.jsonfiler.CustomJsonView;

import lombok.Data;

/**
 * @author kleenxcoder
 */

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
    long id;
	
	@Column
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
	private String firstName;
	
	@Column
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
	private String lastName;
	
	@Column
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
	private String userName;
	
	@Column
	@JsonView(CustomJsonView.UserActionView.class)
	private String password;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    @JsonView({CustomJsonView.UserView.class})
    private List<UserAction> userAction = new ArrayList<>();
    
    public UserAction getLastAction() {
    	Collections.sort(getUserAction(),( new UserActionComparator()).reversed());
    	return getUserAction().iterator().next();
    }

}