package com.hospital.daos;

import com.hospital.models.User;
import java.util.Optional;

public interface UserDAO extends CrudDAO<User, Integer> {
    Optional<User> findByEmail(String email);
    boolean validateCredentials(String email, String password);
}