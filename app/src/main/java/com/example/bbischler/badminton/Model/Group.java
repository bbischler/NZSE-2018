package com.example.bbischler.badminton.Model;

import java.util.ArrayList;

public class Group {
    String name;

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    ArrayList<Training> trainings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    String groupCode;

    public Group(String name, String groupCode) {
        this.name = name;
        this.groupCode = groupCode;
        trainings = new ArrayList<>();
    }
}
