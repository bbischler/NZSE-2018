package com.example.bbischler.badminton;

import java.util.ArrayList;
import java.util.Date;

public class Training {


    Integer id;
    String name;
    Date datum;
    Date beginTime;
    Date endTime;
    ArrayList<Exercise> excersises;

    public Training(Integer id, String name, Date datum, Date beginTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.datum = datum;
        this.beginTime = beginTime;
        this.endTime = endTime;

    }


    public ArrayList<Exercise> getExcersises() { return excersises; }

    public void setExcersises(ArrayList<Exercise> excersises) { this.excersises = excersises; }

    public Integer getId() {
        return id;
    }
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
