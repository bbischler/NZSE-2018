package com.example.bbischler.badminton.Exercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;

import java.util.ArrayList;
import java.util.List;

public class NewExercisePopop extends Activity {
    IBadmintonServiceInterface service;
    Button btn_Abort;
    Button btn_AddNewExercise;
    List<String> exerciseNames;
    int trainingID;

    EditText textDescription;
    EditText textMinutes;
    EditText textTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new MockBadmintonService();
        setContentView(R.layout.create_exercise);

        textDescription = findViewById(R.id.txt_newExerciseDesc);
        textMinutes = findViewById(R.id.txt_newExerciseMinutes);
        textTitle = findViewById(R.id.txt_newExerciseName);

        Bundle b = getIntent().getExtras();
        trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");

        exerciseNames = new ArrayList<>();
        ArrayList<Exercise> exercises = service.getExercises();
        for(Exercise e : exercises)
            exerciseNames.add(e.getName());

        btn_Abort = findViewById(R.id.button_abort);
        btn_AddNewExercise = findViewById(R.id.button_addNewExercise);
        btn_Abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Abort_onClick(v);
            }
        });
        btn_AddNewExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_AddNewExercise_onClick(v);
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.85));
    }

    private void btn_AddNewExercise_onClick(View v) {
        String name = textTitle.getText().toString();
        String desc = textDescription.getText().toString();
        String minutesText = textMinutes.getText().toString();

        if(name.isEmpty() || minutesText.isEmpty())
            return;

        int minutes = Integer.parseInt(minutesText);

        if(exerciseNames.contains(name)){
            Toast.makeText(this, name + " ist bereits vorhanden", Toast.LENGTH_SHORT).show();
            return;
        }

        Exercise e = new Exercise(0, name, desc);
        service.addExercise(e);

        TrainingExercise te = new TrainingExercise(e, minutes);
        service.addExerciseForTraining(te, trainingID);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void btn_Abort_onClick(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
