package com.hospital.services;

import com.hospital.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(int id);
    boolean create(User user);
    boolean update(User user);
    boolean delete(int id);
    boolean validateCredentials(String email, String password);
}
