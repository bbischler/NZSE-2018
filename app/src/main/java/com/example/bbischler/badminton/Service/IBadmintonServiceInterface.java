package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.User;

import java.util.ArrayList;

public interface IBadmintonServiceInterface {
    User login(String username, String passwordHash);
    void connectToGroup(String groupCode);
    ArrayList<Training> getTrainingsForGroup(String groupCode);
    void cancelTraining(Integer id);
    Training getTraining(int id);
}
