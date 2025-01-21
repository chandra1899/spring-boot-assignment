package com.assignment.assignment1.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private String category;
}
