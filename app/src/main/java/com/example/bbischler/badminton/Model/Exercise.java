package com.example.bbischler.badminton.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.Destroyable;

public class Exercise {


    Integer id;
    String name;
    Integer duration;
    String description;

    public Exercise(Integer id, String name, Integer duration, String description) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.description = description;

    }


    // Getter //


    public String getDuration() {
        return duration + " min";
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter //


    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

}
