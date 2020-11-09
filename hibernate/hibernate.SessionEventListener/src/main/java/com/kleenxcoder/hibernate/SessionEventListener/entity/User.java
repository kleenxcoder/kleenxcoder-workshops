package com.kleenxcoder.hibernate.SessionEventListener.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author kleenxcoder
 */

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String userName;
	
	@Column
	private String password;

}