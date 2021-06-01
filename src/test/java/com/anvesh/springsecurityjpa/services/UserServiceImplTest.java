package com.anvesh.springsecurityjpa.services;

import com.anvesh.springsecurityjpa.model.User;
import com.anvesh.springsecurityjpa.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    @Mock
    UserService service;

    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl useimplementation;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUsername() {
        User user = new User();
        user.setUsername("anvesh");
        when(repository.findByUsername(anyString())).thenReturn(Optional.of(user));
        User user1 = useimplementation.findByUsername("anvesh");
        assertNotNull(user1);
        assertEquals("anvesh", user1.getUsername());
    }

    @Test
    void userAlreadyExists() {
        User user = new User();
        user.setUsername("anvesh");
        when(repository.findByUsername(anyString())).thenReturn(Optional.empty());
        Boolean isuseralreadyExists = useimplementation.userAlreadyExists("anvesh");
        assertNotNull(isuseralreadyExists);
        assertFalse(isuseralreadyExists);
    }
}