package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.model.UserExpenses;
import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.service.ExpensesService;
import com.assignment.assignment1.service.UserExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/expenses")
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;
    @Autowired
    private UserExpensesService userExpensesService;

    @PostMapping("/expense")
    public ResponseEntity<Expenses> createExpense(@RequestBody Expenses expense, @RequestAttribute("user") Users user) {
//        if()
        Expenses exp = expensesService.createExpense(expense);

        UserExpenses userExpenses = new UserExpenses(user.getId(), exp.getId());
        userExpensesService.createUserExpense(userExpenses);

        return new ResponseEntity<>(exp, HttpStatus.OK);
    }
}
