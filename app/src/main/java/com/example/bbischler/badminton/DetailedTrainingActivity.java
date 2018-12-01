package com.example.bbischler.badminton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class DetailedTrainingActivity extends AppCompatActivity {
    Training training;
    private exerciseAdapter _exerciseAdapter;
    ArrayList<Exercise> excersises = new ArrayList<>();
    String description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
    String descriptionExercise = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_training);

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        Bundle b = getIntent().getExtras();
        int trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");

        training = new Training(trainingID, "testTraining", new Date(), new Date(), new Date(), description);

        final ListView listview = findViewById(R.id.exerciseList);

        for (int i = 0; i < 4; i++) {
            excersises.add(new Exercise(i, "exercisename" + i, 10 + i, descriptionExercise));
        }
        training.setExcersises(excersises);

        TextView trainingTime = (TextView) findViewById(R.id.textView_time);
        trainingTime.setText(training.getTime());
        TextView trainingDate = (TextView) findViewById(R.id.textView_date);
        trainingDate.setText(training.getDatum());
        TextView trainingDesc = (TextView) findViewById(R.id.textView_desc);
        trainingDesc.setText(training.getDescription());

        final ListView listview2 = (ListView) findViewById(R.id.exerciseList);


        _exerciseAdapter = new exerciseAdapter(this, excersises);
        listview.setAdapter(_exerciseAdapter);



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
