package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Users;
import org.springframework.web.bind.annotation.*;

@RestController
public class Home {
//        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping("/")
        public String greet(@RequestAttribute("user") Users user) {
            return "Welcome Home ! " + user.getName();
        }
}
