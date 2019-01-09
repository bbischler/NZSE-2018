package com.example.bbischler.badminton.Details;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;

import java.util.ArrayList;
import java.util.List;

public class NewExercisePopop extends Activity {
    IBadmintonServiceInterface service;
    Button btn_Abort;
    Button btn_AddNewExercise;
    List<Integer> timerIntervalls = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new MockBadmintonService();
        setContentView(R.layout.create_exercise);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));


        timerIntervalls.addAll(service.getTimeIntervalls());

        Spinner dropdownExerciseTimes = findViewById(R.id.spinner_newExerciseTime);
        ArrayAdapter<Integer> adapterTimes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adapterTimes.addAll(timerIntervalls);
        dropdownExerciseTimes.setAdapter(adapterTimes);


    }
}
