package com.events.service;

import com.events.model.User;

public interface AuthService {
    User login(String username, String password);
}
