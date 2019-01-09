package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.User;
import com.example.bbischler.badminton.Model.Exercise;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MockBadmintonService implements IBadmintonServiceInterface {
    @Override
    public User login(String email, String password) {
        for (User user : LocalDatabase.getInstance().Trainer) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;
        }

        return null;
    }

    @Override
    public void connectToGroup(String groupCode) {

    }

    public ArrayList<Training> getTrainingsForGroup(String groupCode) {
        ArrayList<Training> trainings = new ArrayList<>();

        for (Group gruppe : LocalDatabase.getInstance().Gruppen) {
            if (gruppe.getGroupCode().equals(groupCode)) {
                trainings.addAll(gruppe.getTrainings());
            }
        }

        return trainings;
    }

    @Override
    public void cancelTraining(Integer id) {
        Training obj = null;

        for (Training t : LocalDatabase.getInstance().Trainings)
            if (t.getId() == id)
                obj = t;
        if (obj != null)
            obj.setCancelled(true);
    }

    @Override
    public Training getTraining(int id) {
        Training obj = null;

        for (Training t : LocalDatabase.getInstance().Trainings)
            if (t.getId() == id)
                return t;

        return null;
    }

    @Override
    public ArrayList<Exercise> getExercises() {
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises = LocalDatabase.getInstance().Exercises;
        return exercises;
    }

    @Override
    public ArrayList<Integer> getTimeIntervalls() {
        ArrayList<Integer> timeintervalls = new ArrayList<>();
        timeintervalls = LocalDatabase.getInstance().TimeIntervalls;
        return timeintervalls;
    }
}
