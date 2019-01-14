package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.Model.User;
import com.example.bbischler.badminton.Model.Exercise;

import java.util.ArrayList;

public interface IBadmintonServiceInterface {
    User login(String username, String passwordHash);

    void connectToGroup(String groupCode);

    ArrayList<Training> getTrainingsForGroup(String groupCode);


    void addTraining(String groupId, Training t);
    void cancelTraining(Integer id);

    Training getTraining(int id);

    ArrayList<Exercise> getExercises();

    ArrayList<Integer> getTimeIntervalls();

    String getNameForGroupId(String groupId);

    void addExerciseForTraining(TrainingExercise te, int trainingId);
    void addExercise(Exercise e);

    void deleteExerciseAtPosition(int trainingId, int position);
    void setExercisesForTraining( int trainingid, ArrayList<TrainingExercise> exercises);
}
