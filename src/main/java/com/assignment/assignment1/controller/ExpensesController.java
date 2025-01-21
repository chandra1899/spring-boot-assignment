package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.model.UserExpenses;
import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.service.ExpensesService;
import com.assignment.assignment1.service.UserExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
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

    @DeleteMapping("/expense/{expId}")
    public ResponseEntity<?> deleteExpense(@PathVariable int expId, @RequestAttribute("user") Users user) {
        Expenses expense = expensesService.getExpenseById(expId);
        if(expense == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense Doesn't Exits");
        UserExpenses userExpense = userExpensesService.getById(expId);
        if(userExpense.getUserid() != user.getId())
            return new ResponseEntity<>("You Don't have permission", HttpStatus.UNAUTHORIZED);
        expensesService.deleteExpenseById(expId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
