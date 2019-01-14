package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.TrainingExercise;
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

    private Group getGruppe(String groupId){
        Group obj = null;

        for (Group g : LocalDatabase.getInstance().Gruppen)
            if (g.getGroupCode().equals(groupId))
                return g;

        return null;
    }

    @Override
    public void addTraining(String groupId, Training t) {
        Group g = getGruppe(groupId);

        int highestId = 0;
        for(Training tr : LocalDatabase.getInstance().Trainings){
            if(tr.getId() > highestId)
                highestId = tr.getId();
        }
        t.setId(highestId+1);
        g.getTrainings().add(t);
        LocalDatabase.getInstance().Trainings.add(t);
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

    @Override
    public String getNameForGroupId(String groupId) {
        for(Group g : LocalDatabase.getInstance().Gruppen){
            if(g.getGroupCode().equals(groupId)){
                return g.getName();
            }
        }
        return "";
    }

    @Override
    public void addExerciseForTraining(TrainingExercise te, int trainingId) {
        Training t = getTraining(trainingId);
        t.getExcersises().add(te);
    }

    @Override
    public void addExercise(Exercise e) {
        int highestId = 0;
        for(Exercise ex : LocalDatabase.getInstance().Exercises){
            if(ex.getId() > highestId)
                highestId = ex.getId();
        }
        e.setId(highestId+1);
        LocalDatabase.getInstance().Exercises.add(e);
    }

    @Override
    public void deleteExerciseAtPosition(int trainingId, int position) {

    }

    @Override
    public void setExercisesForTraining(int trainingid, ArrayList<TrainingExercise> exercises) {
        Training t = getTraining(trainingid);
        t.setExcersises(exercises);
    }
}
