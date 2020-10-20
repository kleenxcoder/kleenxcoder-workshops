package com.kleenxcoder.spring.rest.jackson.entity.comparator;

import java.util.Comparator;

import com.kleenxcoder.spring.rest.jackson.entity.UserAction;

/**
 * @author kleenxcoder
 */

public class UserActionComparator implements Comparator<UserAction> {
 
    @Override
    public int compare(UserAction fist, UserAction second) {
       return Long.compare(fist.getId(), second.getId());
    }
 
}