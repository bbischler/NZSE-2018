package com.example.bbischler.badminton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exercise {


    Integer id;
    String name;


    Date startTime;
    Date endTime;

    public Exercise(Integer id, String name, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    // Getter //

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter //
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(startTime )+ " - " + sdf.format(endTime) + " " + name;
    }

}
