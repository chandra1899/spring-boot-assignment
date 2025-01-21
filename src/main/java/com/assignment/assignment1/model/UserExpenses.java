package com.assignment.assignment1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
public class UserExpenses {
    private int userid;
    @Id
    private int expenseId;

    public UserExpenses() {

    }

    public UserExpenses(int userId, int expId) {
        this.userid = userId;
        this.expenseId = expId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }
}
