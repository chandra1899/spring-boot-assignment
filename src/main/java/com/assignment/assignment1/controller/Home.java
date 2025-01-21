package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
        @GetMapping("/")
        public String greet(@RequestAttribute("user") Users user) {
            return "Welcome Home !" + user.getEmail();
        }
}
