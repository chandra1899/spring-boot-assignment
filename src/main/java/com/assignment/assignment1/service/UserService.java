package com.assignment.assignment1.service;

import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void createUser(Users user) {
        userRepo.save(user);
    }

    public boolean checkUserExits(Users user) {
       return userRepo.findByEmail(user.getEmail()) != null;
    }

    public Users getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
