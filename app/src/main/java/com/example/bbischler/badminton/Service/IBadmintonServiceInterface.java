package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Training;

import java.util.ArrayList;

public interface IBadmintonServiceInterface {
    void login(String username, String passwordHash);
    void connectToGroup(String groupCode);
    ArrayList<Training> getTrainingsForGroup(String groupCode);
}
