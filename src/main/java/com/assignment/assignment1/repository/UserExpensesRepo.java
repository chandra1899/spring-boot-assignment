package com.assignment.assignment1.repository;

import com.assignment.assignment1.model.UserExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpensesRepo extends JpaRepository<UserExpenses, Integer> {

}
