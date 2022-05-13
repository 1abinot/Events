package com.events.service.impl;

import com.events.model.User;
import com.events.model.exceptions.InvalidArgumentsException;
import com.events.model.exceptions.InvalidUserCredentialsException;
import com.events.repository.jpa.UserRepository;
import com.events.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
