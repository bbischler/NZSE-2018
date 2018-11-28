package com.example.bbischler.badminton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Training {


    Integer id;
    String name;
    Date datum;
    Date beginTime;
    Date endTime;
    String description;
    ArrayList<Exercise> excersises;

    public Training(Integer id, String name, Date datum, Date beginTime, Date endTime, String description) {
        this.id = id;
        this.name = name;
        this.datum = datum;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.description = description;

    }


    public ArrayList<Exercise> getExcersises() {
        return excersises;
    }

    public void setExcersises(ArrayList<Exercise> excersises) {
        this.excersises = excersises;
    }

    public Integer getId() {
        return id;
    }

    public String getDatum() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        return sdf.format(datum);
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeginTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(beginTime);
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTime() {
        return getBeginTime() + " - " + getEndTime();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

}
