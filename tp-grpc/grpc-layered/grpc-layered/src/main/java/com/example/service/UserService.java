package com.example.service;

import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String name, String email);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    boolean deleteUser(Long id);
}
