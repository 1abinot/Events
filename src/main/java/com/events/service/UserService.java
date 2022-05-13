package com.events.service;

import com.events.model.User;

public interface UserService {
    User register(String username, String password, String repeatPassword, String name, String surname, String email);
}
