package com.anvesh.springsecurityjpa.services;


import com.anvesh.springsecurityjpa.model.User;
import com.anvesh.springsecurityjpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jpa")
public class MyUserDetailsServiceImpJPA implements MyUserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsServiceImpJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails findUserByName(String s) {
        return convertToUSerDetails(s);
    }

    public UserDetails convertToUSerDetails(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));
        return new MyUserDetails(user);

    }
}
