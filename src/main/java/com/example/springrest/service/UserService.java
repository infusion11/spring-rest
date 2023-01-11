package com.example.springrest.service;

import com.example.springrest.model.User;

import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUser(String username);
    void giveRoleToUser(String username, String role);
}
