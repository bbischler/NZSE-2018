package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Login.TrainerLoginActivity;
import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.User;
import com.example.bbischler.badminton.Model.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LocalDatabase {
    private static LocalDatabase instance;

    public ArrayList<Training> Trainings;
    public ArrayList<Group> Gruppen;
    public ArrayList<User> Trainer;
    public ArrayList<Exercise> Exercises;
    public ArrayList<Integer> TimeIntervalls;
    public ArrayList<String> groupNames;

    public static LocalDatabase getInstance() {
        if (instance == null)
            instance = new LocalDatabase();
        return instance;
    }

    public LocalDatabase() {
        String description = "In diesem Training werden wir vor allem Aufschläge üben. Sowohl Vorhand als auch Rückhand. Sollte dann noch Zeit sein werden wir den Netzdrop üben. Zu Letzt gibt es einige Übungen zum Smash";
        Trainings = new ArrayList<>();
        Exercises = new ArrayList<>();
        TimeIntervalls = new ArrayList<>();
        Gruppen = new ArrayList<>();
        Trainer = new ArrayList<>();
        groupNames = new ArrayList<>();

        groupNames.add("Anfänger Darmstadt");
        groupNames.add("Ligamannschaft");

        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                Trainings.add(new Training(i, groupNames.get(0), new Date(), new Date(), new Date(), description, 11 + i));
            } else {
                Trainings.add(new Training(i, groupNames.get(1), new Date(), new Date(), new Date(), description, 10 + i));

            }
        }

//        Training t1 = new Training(1, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description, 14);
//        Training t2 = new Training(2, "Ligamannschaft", new Date(), new Date(), new Date(), description, 13);
//        Training t3 = new Training(3, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description, 12);
//        Training t4 = new Training(4, "Ligamannschaft", new Date(), new Date(), new Date(), description, 20);
//
//        Trainings.add(t1);
//        Trainings.add(t2);
//        Trainings.add(t3);
//        Trainings.add(t4);

        Exercise e1 = new Exercise(1, "Aufschlag - Vorhand", 20, "Hier werden wir Vorhandaufschläge üben");
        Exercise e2 = new Exercise(2, "Aufschlag - Rückhand", 20, "Hier werden wir Rückhandaufschläge üben");
        Exercise e3 = new Exercise(3, "Netzdrop - Vorhand", 10, "Netzdrop-Übung mit Zuwurf");
        Exercise e4 = new Exercise(4, "Netzdrop - Rückhand", 10, "Netzdrop-Übung mit Zuwurf");
        Exercise e5 = new Exercise(5, "Smash - Vorhand", 10, "Clear Übung auf dem ganzen Feld - Vorhand");
        Exercise e6 = new Exercise(6, "Smash - Rückhand", 10, "Clear Übung auf dem ganzen Feld - Rückhand");

        Exercises.add(e1);
        Exercises.add(e2);
        Exercises.add(e3);
        Exercises.add(e4);
        Exercises.add(e5);
        Exercises.add(e6);

        for (int i = 1; i <= 10; i++) {
            TimeIntervalls.add(i * 10);
        }

        Group da = new Group(groupNames.get(0), "DA123");
        Group liga = new Group(groupNames.get(1), "LIGA123");
        for (int i = 0; i < Trainings.size(); i++) {
            if (Trainings.get(i).getName() == groupNames.get(0)) {
                da.getTrainings().add(Trainings.get(i));
            } else if (Trainings.get(i).getName() == groupNames.get(1)) {
                liga.getTrainings().add(Trainings.get(i));
            }
        }
//        da.getTrainings().add(t1);
//        da.getTrainings().add(t3);
//
//        liga.getTrainings().add(t2);
//        liga.getTrainings().add(t4);

        Gruppen.add(da);
        Gruppen.add(liga);

        User trainer1 = new User("Marco", "marco@mail.de", "12345");
        trainer1.getGroups().add("DA123");
        Trainer.add(trainer1);
    }
}
