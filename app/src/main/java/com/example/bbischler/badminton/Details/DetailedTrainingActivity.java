package com.example.bbischler.badminton.Details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailedTrainingActivity extends AppCompatActivity implements StartDragListener {
    Training training;
    RecyclerViewAdapter mAdapter;
    ItemTouchHelper touchHelper;
    TextView trainingTime;
    TextView trainingDate;
    TextView trainingDesc;
    private RecyclerView recyclerview;
    List<Exercise> excersises = new ArrayList<>();
    String description = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
    String descriptionExercise = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_training);
        Bundle b = getIntent().getExtras();
        int trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");
        training = new Training(trainingID, "testTraining", new Date(), new Date(), new Date(), description, 14);
        recyclerview = (RecyclerView) findViewById(R.id.exerciseList);
        mAdapter = new RecyclerViewAdapter(excersises, this);

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        Mock Exercises
        for (int i = 0; i < 8; i++) {
            excersises.add(new Exercise(i, "Übung Nr. " + i, 10 + i, descriptionExercise));
        }
        training.setExcersises(excersises);

        trainingTime = (TextView) findViewById(R.id.textView_time);
        trainingTime.setText(training.getTime());
        trainingDate = (TextView) findViewById(R.id.textView_date);
        trainingDate.setText(training.getDatum());
        trainingDesc = (TextView) findViewById(R.id.textView_desc);
        trainingDesc.setText(training.getDescription());
        TextView numberStudents = (TextView) findViewById(R.id.textView_numberStudents);
        numberStudents.setText(training.getStudentsNumber());


        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerview);
        recyclerview.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setFocusable(false);

    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }
}
