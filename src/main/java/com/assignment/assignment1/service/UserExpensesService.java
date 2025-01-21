package com.assignment.assignment1.service;

import com.assignment.assignment1.model.UserExpenses;
import com.assignment.assignment1.repository.UserExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExpensesService {
    @Autowired
    private UserExpensesRepo userExpensesRepo;


    public void createUserExpense(UserExpenses userExpenses) {
        userExpensesRepo.save(userExpenses);
    }
}
