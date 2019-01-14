package com.example.bbischler.badminton.Service;

import com.example.bbischler.badminton.Login.TrainerLoginActivity;
import com.example.bbischler.badminton.Model.Group;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.Model.User;
import com.example.bbischler.badminton.Model.Exercise;

import java.text.SimpleDateFormat;
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
        try {


            String description = "In diesem Training werden wir vor allem Aufschläge üben. Sowohl Vorhand als auch Rückhand. Sollte dann noch Zeit sein werden wir den Netzdrop üben. Zu Letzt gibt es einige Übungen zum Smash";
            Trainings = new ArrayList<>();
            Exercises = new ArrayList<>();
            TimeIntervalls = new ArrayList<>();
            Gruppen = new ArrayList<>();
            Trainer = new ArrayList<>();
            groupNames = new ArrayList<>();

            groupNames.add("Anfänger Darmstadt");
            groupNames.add("Ligamannschaft");

        /*
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                Trainings.add(new Training(i, groupNames.get(0), new Date(), new Date(), new Date(), description, 11 + i));
            } else {
                Trainings.add(new Training(i, groupNames.get(1), new Date(), new Date(), new Date(), description, 10 + i));

            }
        }
        */

            Exercise e1 = new Exercise(1, "Aufschlag - Vorhand", "Hier werden wir Vorhandaufschläge üben");
            Exercise e2 = new Exercise(2, "Aufschlag - Rückhand", "Hier werden wir Rückhandaufschläge üben");
            Exercise e3 = new Exercise(3, "Netzdrop - Vorhand", "Netzdrop-Übung mit Zuwurf");
            Exercise e4 = new Exercise(4, "Netzdrop - Rückhand", "Netzdrop-Übung mit Zuwurf");
            Exercise e5 = new Exercise(5, "Smash - Vorhand", "Clear Übung auf dem ganzen Feld - Vorhand");
            Exercise e6 = new Exercise(6, "Smash - Rückhand", "Clear Übung auf dem ganzen Feld - Rückhand");

            Exercises.add(e1);
            Exercises.add(e2);
            Exercises.add(e3);
            Exercises.add(e4);
            Exercises.add(e5);
            Exercises.add(e6);

            Date d11 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190115 20:00").getTime());
            Date d12 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190115 21:00").getTime());
            Date d21 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190116 19:00").getTime());
            Date d22 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190116 21:00").getTime());
            Date d31 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190122 20:00").getTime());
            Date d32 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190122 21:00").getTime());
            Date d41 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190123 19:00").getTime());
            Date d42 = new Date (new SimpleDateFormat("yyyyMMdd hh:mm").parse("20190123 21:00").getTime());

            Training t1 = new Training(1, "Anfänger Darmstadt", d11, d11, d12, "Aufschlagtraining Vor- und Rückhand", 14);
            Training t2 = new Training(2, "Ligamannschaft", d21, d21, d22, "Aufschlagtraining Vor- und Rückhand", 13);
            Training t3 = new Training(3, "Anfänger Darmstadt", d31, d31, d32, "Netzdrop Vor- und Rückhand", 12);
            Training t4 = new Training(4, "Ligamannschaft", d41, d41, d42, "Smash Vor- und Rückhand", 20);

            t1.getExcersises().add(new TrainingExercise(e1, 20));
            t1.getExcersises().add(new TrainingExercise(e2, 20));

            t2.getExcersises().add(new TrainingExercise(e1, 30));
            t2.getExcersises().add(new TrainingExercise(e2, 45));

            t3.getExcersises().add(new TrainingExercise(e3, 20));
            t3.getExcersises().add(new TrainingExercise(e4, 20));

            t4.getExcersises().add(new TrainingExercise(e5, 20));
            t4.getExcersises().add(new TrainingExercise(e6, 20));

            Trainings.add(t1);
            Trainings.add(t2);
            Trainings.add(t3);
            Trainings.add(t4);

            for (int i = 1; i <= 10; i++) {
                TimeIntervalls.add(i * 10);
            }

            Group da = new Group(groupNames.get(0), "DA123");
            Group liga = new Group(groupNames.get(1), "LIGA123");
        /*
        for (int i = 0; i < Trainings.size(); i++) {
            if (Trainings.get(i).getName() == groupNames.get(0)) {
                da.getTrainings().add(Trainings.get(i));
            } else if (Trainings.get(i).getName() == groupNames.get(1)) {
                liga.getTrainings().add(Trainings.get(i));
            }
        }
        */

            da.getTrainings().add(t1);
            da.getTrainings().add(t3);

            liga.getTrainings().add(t2);
            liga.getTrainings().add(t4);

            Gruppen.add(da);
            Gruppen.add(liga);

            User trainer1 = new User("Marco", "marco@mail.de", "12345");
            trainer1.getGroups().add("DA123");
            Trainer.add(trainer1);
        }
        catch(Exception e){}
    }
}
