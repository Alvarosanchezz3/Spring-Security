package com.alvaro.springsecurity.service.impl;

import com.alvaro.springsecurity.dto.SaveUser;
import com.alvaro.springsecurity.exception.InvalidPasswordException;
import com.alvaro.springsecurity.persistence.entitiy.User;
import com.alvaro.springsecurity.persistence.repository.UserRepository;
import com.alvaro.springsecurity.service.UserService;
import com.alvaro.springsecurity.persistence.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerOneCustomer(SaveUser newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getName());
        user.setUsername(newUser.getUsername());
        user.setRole(Role.CUSTOMER);

        return userRepository.save(user);
    }

    // En el UserServiceImpl lo implementamos
    @Override
    public Optional<User> findOneByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para la validación de la contraseña
    private void validatePassword(SaveUser dto) {

        if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords don't match");
        }

        if (!dto.getPassword().equals(dto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
