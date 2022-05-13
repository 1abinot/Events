package com.events.service.impl;

import com.events.model.User;
import com.events.model.exceptions.*;
import com.events.repository.jpa.UserRepository;
import com.events.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, String email) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if(this.userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyExistsException(email);


        User user = new User(username,password,name,surname, email);
        return userRepository.save(user);
    }
}
