package com.assignment.assignment1.service;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.repository.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepo expenseRepo;

    public Expenses createExpense(Expenses expense) {
        return expenseRepo.save(expense);
    }
}
