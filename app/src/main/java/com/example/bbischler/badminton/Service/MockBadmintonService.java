package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MockBadmintonService implements IBadmintonServiceInterface {
    @Override
    public void login(String username, String passwordHash) {

    }

    @Override
    public void connectToGroup(String groupCode) {

    }

    public ArrayList<Training> getTrainingsForGroup(String groupCode){
        ArrayList<Training> trainings = new ArrayList<>();

        for(Group gruppe : LocalDatabase.getInstance().Gruppen){
            if(gruppe.getGroupCode().equals(groupCode)){
                trainings.addAll(gruppe.getTrainings());
            }
        }

        return trainings;
    }


}
