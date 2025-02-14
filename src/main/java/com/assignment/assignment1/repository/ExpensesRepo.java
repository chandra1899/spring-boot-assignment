package com.assignment.assignment1.repository;

import com.assignment.assignment1.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses, Integer> {
    @Query("SELECT e FROM Expenses e " +
            "JOIN UserExpenses ue ON ue.expenseId = e.id " +
            "WHERE ue.userid = :userId")
    List<Expenses> findAllExpensesByUserId(int userId);
    List<Expenses> findAllByCategory(String category);
    @Query("SELECT e FROM Expenses e "+
            "JOIN UserExpenses ue ON ue.expenseId = e.id " +
            "WHERE ue.userid = :userId"+
            "  AND (LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "  OR LOWER(e.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Expenses> findExpenses(String keyword, int userId);
}
