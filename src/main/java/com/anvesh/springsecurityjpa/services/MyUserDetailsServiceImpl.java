package com.anvesh.springsecurityjpa.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

//To return a hardcoded user
@Service("hardcoded")
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    @Override
    public UserDetails findUserByName(String s) {
        return new MyUserDetails(s);
    }
}
