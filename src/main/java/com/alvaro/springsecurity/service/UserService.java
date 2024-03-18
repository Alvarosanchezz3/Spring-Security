package com.alvaro.springsecurity.service;

import com.alvaro.springsecurity.dto.SaveUser;
import com.alvaro.springsecurity.persistence.entitiy.User;

import java.util.Optional;

public interface UserService {
    User registerOneCustomer(SaveUser newUser);

    Optional<User> findOneByUsername(String username);
}
