package com.kleenxcoder.hibernate.SessionEventListener.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import com.kleenxcoder.hibernate.SessionEventListener.entity.User;

/**
 * @author kleenxcoder
 */

public interface UserRepository extends CrudRepository<User, Long> {

	@QueryHints(value = { @QueryHint(name = "org.hibernate.commen", value = "mycomment")},
    forCounting = false)
	public List<User> findByFirstName(String firstName);
	
	

}
