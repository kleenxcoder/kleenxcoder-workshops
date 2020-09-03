package com.kleenxcoder.mapstruct.repository;

import org.springframework.data.repository.CrudRepository;

import com.kleenxcoder.mapstruct.entity.User;

/**
 * @author kleenxcoder
 */

public interface UserRepository extends CrudRepository<User, Long> {

}
