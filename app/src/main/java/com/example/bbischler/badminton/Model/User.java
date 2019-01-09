package com.example.bbischler.badminton.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String Password;

    public ArrayList<String> getGroups() {
        return groups;
    }

    private ArrayList<String> groups;

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
        groups = new ArrayList<String>();
    }
}