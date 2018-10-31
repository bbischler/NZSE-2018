package com.example.bbischler.badminton;

import java.util.ArrayList;
import java.util.Date;

public class Training {


    Integer id;
    String name;
    ArrayList<Exercise> excersises;


    public Training(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ArrayList<Exercise> getExcersises() { return excersises; }

    public void setExcersises(ArrayList<Exercise> excersises) { this.excersises = excersises; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
