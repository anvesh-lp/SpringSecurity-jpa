package com.anvesh.springsecurityjpa.services;

import com.anvesh.springsecurityjpa.model.User;

public interface UserService {

    User findByUsername(String s);

    Boolean userAlreadyExists(String s);
}
