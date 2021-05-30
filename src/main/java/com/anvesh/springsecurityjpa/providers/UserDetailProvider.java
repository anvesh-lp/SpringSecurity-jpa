package com.anvesh.springsecurityjpa.providers;

import com.anvesh.springsecurityjpa.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailProvider implements UserDetailsService {

    private final MyUserDetailsService userDetailsService;

    public UserDetailProvider(@Qualifier("jpa") MyUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDetailsService.findUserByName(s);
    }
}
