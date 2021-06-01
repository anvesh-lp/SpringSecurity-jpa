package com.anvesh.springsecurityjpa.services;

import com.anvesh.springsecurityjpa.model.User;
import com.anvesh.springsecurityjpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String s) {
        Optional<User> user = userRepository.findByUsername(s);
        return user.orElseThrow(() -> new RuntimeException("User Not found " + s));
    }

    @Override
    public Boolean userAlreadyExists(String s) {
        try {
            findByUsername(s);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
}
