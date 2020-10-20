package com.kleenxcoder.spring.rest.jackson.repository;

import org.springframework.data.repository.CrudRepository;

import com.kleenxcoder.spring.rest.jackson.entity.UserAction;

/**
 * @author kleenxcoder
 */

public interface UserActionRepository extends CrudRepository<UserAction, Long> {

}
