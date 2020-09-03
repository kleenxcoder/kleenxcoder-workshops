package com.kleenxcoder.mapstruct.entity;

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

import com.kleenxcoder.mapstruct.entity.comparator.UserActionComparator;

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
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserAction> userAction = new ArrayList<>();
    
    public UserAction getLastAction() {
    	Collections.sort(getUserAction(),( new UserActionComparator()).reversed());
    	return getUserAction().iterator().next();
    }

}
