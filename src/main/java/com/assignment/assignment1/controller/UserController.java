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

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ExpensesService expensesService;
    @Autowired
    private UserExpensesService userExpensesService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expenses>> getAllExpenses(@RequestAttribute(name = "user", required = false) Users user) {
        if(user == null)
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(expensesService.getAllExpenses(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/expense/{expId}")
    public ResponseEntity<?> getExpenseById(@PathVariable int expId, @RequestAttribute(name = "user", required = false) Users user) {
        Expenses exp = expensesService.getExpenseById(expId);
        if(exp == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense Doesn't Exits");
        if(user == null)
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        UserExpenses userExpenses = userExpensesService.getById(expId);
        if(user.getId() != userExpenses.getUserid())
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(exp, HttpStatus.OK);
    }
}