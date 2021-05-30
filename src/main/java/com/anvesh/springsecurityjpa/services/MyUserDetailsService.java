package com.anvesh.springsecurityjpa.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetailsService {
    UserDetails findUserByName(String s);
}
