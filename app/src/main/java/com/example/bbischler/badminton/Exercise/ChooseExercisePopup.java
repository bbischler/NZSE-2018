package com.example.bbischler.badminton.Exercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;

import java.util.ArrayList;
import java.util.List;

public class ChooseExercisePopup extends Activity {
    IBadmintonServiceInterface service;
    List<Exercise> excersises = new ArrayList<>();
    List<String> excersiseNames = new ArrayList<>();
    List<Integer> timerIntervalls = new ArrayList<>();
    Button btn_Abort;
    Button btn_AddExercise;
    Spinner dropdownExerciseNames;
    EditText textMinutes;
    int trainingID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new MockBadmintonService();
        setContentView(R.layout.choose_exercise);
        btn_Abort = findViewById(R.id.button_abort);
        btn_AddExercise = findViewById(R.id.button_addExercise);
        btn_Abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Abort_onClick(v);
            }
        });
        btn_AddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_AddExervise_onClick(v);
            }
        });
        textMinutes = findViewById(R.id.txt_exerciseMinutes);

        Bundle b = getIntent().getExtras();
        trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.85));

        excersises.addAll(service.getExercises());
        for (Exercise e : excersises) {
            excersiseNames.add(e.getName());
        }
        timerIntervalls.addAll(service.getTimeIntervalls());

        dropdownExerciseNames = findViewById(R.id.spinner_exerciseNames);

        ArrayAdapter<String> adapterNames = new ArrayAdapter<String>(
                this, R.layout.spinner_item, excersiseNames
        );

        dropdownExerciseNames.setAdapter(adapterNames);
    }

    private void btn_Abort_onClick(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void btn_AddExervise_onClick(View v) {
        String minutesTesxt = textMinutes.getText().toString();
        if(minutesTesxt.isEmpty())
            return;
        int minutes = Integer.parseInt(minutesTesxt);
        String exerciseName = dropdownExerciseNames.getSelectedItem().toString();
        Exercise selectedExercise = null;
        for(Exercise e : excersises){
            if(e.getName().equals(exerciseName)){
                selectedExercise = e;
                break;
            }
        }

        if(selectedExercise != null){
            TrainingExercise te = new TrainingExercise(selectedExercise, minutes);
            service.addExerciseForTraining(te, trainingID);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
