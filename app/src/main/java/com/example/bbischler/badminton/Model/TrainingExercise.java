package com.example.bbischler.badminton.Model;

public class TrainingExercise{
    private Exercise exercise;
    private int durationMinutes;

    public TrainingExercise(Exercise exercise, int durationMinutes) {
        this.exercise = exercise;
        this.durationMinutes = durationMinutes;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }
}
