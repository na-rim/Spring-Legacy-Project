package org.example.naco.service;

import org.example.naco.domain.User;

import java.util.List;

public interface UserService {
    void register(String username, String rawPassword);
    User authenticate(String username, String rawPassword);
    List<User> findAll();
    void changeRole(Long id, String role);
}
