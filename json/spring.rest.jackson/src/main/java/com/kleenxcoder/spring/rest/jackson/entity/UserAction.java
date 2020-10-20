package com.kleenxcoder.spring.rest.jackson.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.kleenxcoder.spring.rest.jackson.jsonfiler.CustomJsonView;

import lombok.Data;

/**
 * @author kleenxcoder
 */

@Data
@Entity
public class UserAction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
    long id;
	
	@Column
	@JsonView({CustomJsonView.UserView.class, CustomJsonView.UserActionView.class})
	private String action;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn
    @JsonView({CustomJsonView.UserActionView.class})
    User user;

}
