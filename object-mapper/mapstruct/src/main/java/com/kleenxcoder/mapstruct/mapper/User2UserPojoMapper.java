package com.kleenxcoder.mapstruct.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import com.kleenxcoder.mapstruct.entity.User;
import com.kleenxcoder.mapstruct.pojo.UserPojo;

/**
 * @author kleenxcoder
 */

@Mapper(componentModel = "spring")
public interface User2UserPojoMapper {
	
	@Mapping(source = "user.firstName", target = "firstName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
	@Mapping(source = "user.lastName", target = "lastName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    UserPojo map(User user);
	
	default List<UserPojo> map(List<User> user) {
		return null;
	}

}
