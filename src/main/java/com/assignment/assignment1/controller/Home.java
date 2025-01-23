package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Home {
//        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping("/")
        public String greet(@RequestAttribute("user") Users user) {
            if(user == null)
                return "No User";
            return "Welcome Home ! " + user.getName();
        }
}
