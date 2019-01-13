package com.example.bbischler.badminton.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Training {

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public int getNumberParticipants() {
        return numberParticipants;
    }

    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }

    public AcceptState getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(AcceptState acceptState) {
        this.acceptState = acceptState;
    }

    AcceptState acceptState;
    int numberParticipants;
    boolean isCancelled;
    Integer id;
    String name;
    Date datum;
    Date beginTime;
    Date endTime;
    String description;
    ArrayList<TrainingExercise> excersises;
    Integer studentsNumber;

    public Training(Integer id, String name, Date datum, Date beginTime, Date endTime, String description, Integer students) {
        this.id = id;
        this.name = name;
        this.datum = datum;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.description = description;
        this.numberParticipants = students;
        this.acceptState = AcceptState.Unset;
        this.excersises = new ArrayList<TrainingExercise>();
    }


    public ArrayList<TrainingExercise> getExcersises() {
        return excersises;
    }

    public void setExcersises(ArrayList<TrainingExercise> excersises) {
        this.excersises = excersises;
    }

    public Integer getId() {
        return id;
    }

    public String getDatum() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yy");
        return sdf.format(datum);
    }

    public String getDatumMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("DD.MM");
        return sdf.format(datum);
    }

    public String getDatumYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
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

    public String getStudentsNumber() {
        return "Zusagen: " + numberParticipants;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    @Override
    public String toString() {
        return name;
    }

}
