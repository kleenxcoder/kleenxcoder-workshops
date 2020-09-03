package com.kleenxcoder.mapstruct.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author kleenxcoder
 */

@Data
@Entity
public class UserAction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
	
	@Column
	private String action;
	
	@JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn
    User user;

}
