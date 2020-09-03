package com.kleenxcoder.mapstruct.entity.comparator;

import java.util.Comparator;

import com.kleenxcoder.mapstruct.entity.UserAction;

/**
 * @author kleenxcoder
 */

public class UserActionComparator implements Comparator<UserAction> {
 
    @Override
    public int compare(UserAction fist, UserAction second) {
       return Long.compare(fist.getId(), second.getId());
    }
 
}
