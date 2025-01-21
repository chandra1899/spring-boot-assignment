package com.assignment.assignment1.repository;

import com.assignment.assignment1.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
        Users findByEmail(String email);
}
