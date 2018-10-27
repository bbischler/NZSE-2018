package com.example.bbischler.badminton;

public class Training {


    Number id;
    String name;


    public Training(Number id, String name) {
        this.id = id;
        this.name = name;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
