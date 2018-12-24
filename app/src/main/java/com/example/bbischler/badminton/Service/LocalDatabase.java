package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Login.TrainerLoginActivity;
import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;

import java.util.ArrayList;
import java.util.Date;

public class LocalDatabase {
    private static LocalDatabase instance;

    public ArrayList<Training> Trainings;
    public ArrayList<Group> Gruppen;

    public static LocalDatabase getInstance(){
        if(instance == null)
            instance = new LocalDatabase();
        return instance;
    }

    public LocalDatabase(){
        String description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
        Trainings = new ArrayList<>();
        Gruppen = new ArrayList<>();

        Training t1 = new Training(1, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description, 14);
        Training t2 = new Training(2, "Ligamannschaft", new Date(), new Date(), new Date(), description,13);
        Training t3 = new Training(3, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description,12);
        Training t4 = new Training(4, "Ligamannschaft", new Date(), new Date(), new Date(), description,20);

        Group da = new Group("Anfänger Darmstadt", "DA123");
        da.getTrainings().add(t1);
        da.getTrainings().add(t3);

        Group liga = new Group("Ligamannschaft", "LIGA123");
        liga.getTrainings().add(t2);
        liga.getTrainings().add(t4);

        Gruppen.add(da);
        Gruppen.add(liga);
    }
}
