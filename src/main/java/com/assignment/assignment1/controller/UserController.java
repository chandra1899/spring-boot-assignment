package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.model.UserExpenses;
import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expenses>> getAllExpenses(@RequestAttribute("user") Users user) {
        return new ResponseEntity<>(expensesService.getAllExpenses(user.getId()), HttpStatus.OK);
    }
}
