package com.assignment.assignment1.controller;

import com.assignment.assignment1.model.LoginRequest;
import com.assignment.assignment1.model.Users;
import com.assignment.assignment1.service.CryptoService;
import com.assignment.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Authentication {
        @Autowired
        private UserService userService;
        @Autowired
        private CryptoService cryptoService;

        @CrossOrigin()
        @PostMapping("/register")
        public ResponseEntity<?> createUser(@RequestBody Users user) {
            boolean userAlreadyExits = userService.checkUserExits(user);
            if(userAlreadyExits)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exits");
            user.setPassword(cryptoService.encryptText(user.getPassword()));
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("User created successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<?> loginUser(@RequestBody LoginRequest body) {
            Users user = userService.getUserByEmail(body.getUserEmail());
            if(user == null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
            if(!body.getPassword().equals(cryptoService.decryptText(user.getPassword())))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
            String token = body.getUserEmail() + " " + body.getPassword();
            Map<String, String> response = new HashMap<>();
            response.put("token", cryptoService.encryptText(token));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
