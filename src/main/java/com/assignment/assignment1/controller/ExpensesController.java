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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
public class ExpensesController {
    @Autowired
    private ExpensesService expensesService;
    @Autowired
    private UserExpensesService userExpensesService;

    @PostMapping("/expense")
    public ResponseEntity<?> createExpense(@RequestBody Expenses expense, @RequestAttribute("user") Users user) {
        if(user == null)
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        if(expense.getTitle().isEmpty() || expense.getCategory().isEmpty() || expense.getAmount() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Info is missing");
        Expenses exp = expensesService.createExpense(expense);

        UserExpenses userExpenses = new UserExpenses(user.getId(), exp.getId());
        userExpensesService.createUserExpense(userExpenses);

        return new ResponseEntity<>(exp, HttpStatus.OK);
    }

    @DeleteMapping("/expense/{expId}")
    public ResponseEntity<?> deleteExpense(@PathVariable int expId, @RequestAttribute("user") Users user) {
        if(user == null)
            return new ResponseEntity<>(List.of(), HttpStatus.UNAUTHORIZED);
        Expenses expense = expensesService.getExpenseById(expId);
        if(expense == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense Doesn't Exits");

        UserExpenses userExpense = userExpensesService.getById(expId);
        if(userExpense.getUserid() != user.getId())
            return new ResponseEntity<>("You Don't have permission", HttpStatus.UNAUTHORIZED);
        expensesService.deleteExpenseById(expId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PutMapping("/expense")
    public ResponseEntity<?> updateExpenseById(@RequestBody Expenses expense) {
        Expenses exp = expensesService.getExpenseById(expense.getId());
        if(exp == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense Doesn't Exits");

        expensesService.updateExpense(expense);
        return new ResponseEntity<>("Expense updated successfully !", HttpStatus.OK);
    }

    @GetMapping("/expense/search")
    public ResponseEntity<?> findByIdOrCategory(@RequestParam String keyword, @RequestAttribute("user") Users user) {
        if(keyword.isEmpty())
            return new ResponseEntity<>(List.of(), HttpStatus.OK);
        List<Expenses> expenses = expensesService.findExpenses(keyword, user.getId());
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}
