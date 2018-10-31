package com.example.bbischler.badminton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class DetailedTrainingActivity extends AppCompatActivity {
    //    ArrayList<Training> training = new ArrayList<>();
    Training training;
    ArrayList<Exercise> excersises = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_training);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle b = getIntent().getExtras();
        int trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");

        training = new Training(trainingID, "testTraining");

        final ListView listview = findViewById(R.id.exerciseName);

        for (int i = 0; i < 20; i++) {
            excersises.add(new Exercise(i, "exercisename" + i, new Date(), new Date()));
        }
        training.setExcersises(excersises);

        ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(this,
                R.layout.exercise, R.id.textView_exerciseName, training.excersises);
        listview.setAdapter(adapter);

//        TextView test = (TextView) findViewById(R.id.test);
//        test.setText("TrainigsID: " + trainingID);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
