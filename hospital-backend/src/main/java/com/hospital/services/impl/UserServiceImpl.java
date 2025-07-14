package com.hospital.services.impl;

import com.hospital.models.User;
import com.hospital.daos.UserDAO;
import com.hospital.daos.impl.UserDAOImpl;
import com.hospital.services.UserService;
import java.util.List;
import java.util.Optional;


class UserServiceImpl implements UserService {
    private final UserDAO dao = new UserDAOImpl();

    
    public List<User> findAll() { 
        return dao.findAll(); 
    }
    public Optional<User> findById(int id) { 
        return dao.findById(id); 
    }
    public boolean create(User u) { 
        return dao.create(u);
     }
    public boolean update(User u) { 
        return dao.update(u); 
    }
    public boolean delete(int id) { 
        return dao.delete(id); 
    }
    public boolean validateCredentials(String email, String pass) {
         return dao.validateCredentials(email, pass); 
    }
}
