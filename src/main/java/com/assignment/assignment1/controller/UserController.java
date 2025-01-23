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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/expenses")
    public ResponseEntity<List<Expenses>> getAllExpenses(@RequestAttribute(name = "user", required = false) Users user) {
//        System.out.println("user is "+ user);
        if(user == null)
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(expensesService.getAllExpenses(user.getId()), HttpStatus.OK);
    }
}
