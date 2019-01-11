package com.example.bbischler.badminton.Details;

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
import android.widget.Spinner;

import com.example.bbischler.badminton.Model.Exercise;
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

        Spinner dropdownExerciseNames = findViewById(R.id.spinner_exerciseNames);
        Spinner dropdownExerciseTimes = findViewById(R.id.spinner_exerciseTime);

        ArrayAdapter<String> adapterNames = new ArrayAdapter<String>(
                this, R.layout.spinner_item, excersiseNames
        );
        ArrayAdapter<Integer> adapterTimes = new ArrayAdapter<Integer>(
                this, R.layout.spinner_item, timerIntervalls
        );

        dropdownExerciseNames.setAdapter(adapterNames);
        dropdownExerciseTimes.setAdapter(adapterTimes);


    }

    private void btn_Abort_onClick(View v) {

    }

    private void btn_AddExervise_onClick(View v) {

    }
}
