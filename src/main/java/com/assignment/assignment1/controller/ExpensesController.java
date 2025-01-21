package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Expenses;
import com.assignment.assignment1.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/expenses")
public class ExpensesController {
//    @Autowired
//    private ExpensesService expensesService;
//
//    @PostMapping("/expense")
//    public ResponseEntity<Expenses> createExpense(@RequestBody Expenses expense) {
//        Expenses exp = expensesService.createExpense(expense);
//        return new ResponseEntity<>(exp, HttpStatus.OK);
//    }
}
