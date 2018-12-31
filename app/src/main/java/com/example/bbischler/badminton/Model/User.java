package com.example.bbischler.badminton.Model;

public class User {
    private String name;
    private String email;
    private String Password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        Password = password;
    }
}