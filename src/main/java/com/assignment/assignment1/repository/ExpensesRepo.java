package com.assignment.assignment1.repository;

import com.assignment.assignment1.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {

}
