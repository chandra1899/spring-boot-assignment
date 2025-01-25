package com.assignment.assignment1.service;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.repository.ExpensesRepo;
import com.assignment.assignment1.repository.UserExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesService {
    @Autowired
    private ExpensesRepo expenseRepo;
    @Autowired
    private UserExpensesRepo userExpensesRepo;

    public void deleteExpenseById(int expId) {
        expenseRepo.deleteById(expId);
        userExpensesRepo.deleteById(expId);
    }

    public Expenses getExpenseById(int expId) {
        return expenseRepo.findById(expId).orElse(null);
    }

    public Expenses createExpense(Expenses expense) {
        return expenseRepo.save(expense);
    }

    public List<Expenses> getAllExpenses(int id) {
        return expenseRepo.findAllExpensesByUserId(id);
    }

    public void updateExpense(Expenses expense) {
        expenseRepo.save(expense);
    }

    public List<Expenses> getExpensesByCategory(String s) {
        return expenseRepo.findAllByCategory(s);
    }

    public List<Expenses> findExpenses(String keyword, int userId) {
        return expenseRepo.findExpenses(keyword, userId);
    }
}
