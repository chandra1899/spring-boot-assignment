package com.assignment.assignment1.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String userEmail;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String password;

}
