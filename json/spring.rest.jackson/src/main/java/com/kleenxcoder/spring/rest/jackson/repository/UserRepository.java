package com.kleenxcoder.spring.rest.jackson.repository;

import org.springframework.data.repository.CrudRepository;

import com.kleenxcoder.spring.rest.jackson.entity.User;

/**
 * @author kleenxcoder
 */

public interface UserRepository extends CrudRepository<User, Long> {

}
