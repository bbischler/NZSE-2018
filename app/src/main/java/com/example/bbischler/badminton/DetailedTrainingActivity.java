package com.example.bbischler.badminton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class DetailedTrainingActivity extends AppCompatActivity {
    Training training;
    private exerciseAdapter _exerciseAdapter;
    ArrayList<Exercise> excersises = new ArrayList<>();
    String description = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
    String descriptionExercise = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr";


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

        training = new Training(trainingID, "testTraining", new Date(), new Date(), new Date(), description, 14);

//        final ListView listview = findViewById(R.id.exerciseList);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.exerciseList);

        for (int i = 0; i < 8; i++) {
            excersises.add(new Exercise(i, "Übung Nr. " + i, 10 + i, descriptionExercise));
        }
        training.setExcersises(excersises);

        TextView trainingTime = (TextView) findViewById(R.id.textView_time);
        trainingTime.setText(training.getTime());
        TextView trainingDate = (TextView) findViewById(R.id.textView_date);
        trainingDate.setText(training.getDatum());
        TextView trainingDesc = (TextView) findViewById(R.id.textView_desc);
        trainingDesc.setText(training.getDescription());
        TextView numberStudents = (TextView) findViewById(R.id.textView_numberStudents);
        numberStudents.setText(training.getStudentsNumber());

        _exerciseAdapter = new exerciseAdapter(excersises);
        rv.setAdapter(_exerciseAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setNestedScrollingEnabled(false);
        rv.setFocusable(false);
//        _exerciseAdapter = new exerciseAdapter(this, excersises);
//        listview.setAdapter(_exerciseAdapter);
//        listview.addHeaderView(header);


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
