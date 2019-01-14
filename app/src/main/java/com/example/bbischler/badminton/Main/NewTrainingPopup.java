package com.example.bbischler.badminton.Main;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.example.bbischler.badminton.Service.MySession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class NewTrainingPopup extends Activity {
    IBadmintonServiceInterface service;
    Button btn_Abort;
    Button btn_AddNewTraining;
    List<String> groupNames;

    int dateYear = 0;
    int dateMonth = 0;
    int dateDay = 0;
    int fromHour = 0;
    int fromMinute = 0;
    int toHour = 0;
    int toMinute = 0;

    EditText textDate;
    EditText textTimeFrom;
    EditText textTimeTo;
    EditText textDescription;
    Spinner dropDownGroups;
    Calendar cal;

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day

            dateYear = arg1;
            dateMonth = arg2+1;
            dateDay = arg3;

            textDate.setText(new StringBuilder().append(String.format("%02d", arg3)).append(".")
                    .append(String.format("%02d", arg2+1)).append(".").append(arg1));
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeFromListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            // arg1 = hour
            // arg2 = minute

            fromHour = arg1;
            fromMinute = arg2;

            textTimeFrom.setText(new StringBuilder().append(String.format("%02d", arg1)).append(":")
                    .append(String.format("%02d", arg2)));
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeToListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
            // TODO Auto-generated method stub
            // arg1 = hour
            // arg2 = minute

            toHour = arg1;
            toMinute = arg2;

            textTimeTo.setText(new StringBuilder().append(String.format("%02d", arg1)).append(":")
                    .append(String.format("%02d", arg2)));
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new MockBadmintonService();
        setContentView(R.layout.create_training);

        cal = Calendar.getInstance();
        textDate = findViewById(R.id.txt_Datum);
        textTimeFrom = findViewById(R.id.txt_Von);
        textTimeTo = findViewById(R.id.txt_Bis);
        textDescription = findViewById(R.id.txt_description);
        dropDownGroups = findViewById(R.id.spinner_groups);
        btn_Abort = findViewById(R.id.button_abort);
        btn_AddNewTraining = findViewById(R.id.button_addTraining);

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDatePicker();
            }
        });

        textTimeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTimePickerFrom();
            }
        });

        textTimeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTimePickerTo();
            }
        });

        groupNames   = new ArrayList<>();
        groupNames = MySession.loggedInUser.getGroups();

        btn_Abort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Abort_onClick(v);
            }
        });
        btn_AddNewTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_AddNewTraining_onClick(v);
            }
        });

        ArrayAdapter<String> adapterGroups = new ArrayAdapter<String>(
                this, R.layout.spinner_item, groupNames
        );
        dropDownGroups.setAdapter(adapterGroups);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * 0.85), (int) (height * 0.85));
    }

    private void ShowTimePickerTo() {
        TimePickerDialog dlg = new TimePickerDialog(this, myTimeToListener, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(this));
        dlg.show();
    }

    private void ShowTimePickerFrom() {
        TimePickerDialog dlg = new TimePickerDialog(this, myTimeFromListener, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(this));
        dlg.show();
    }

    private void ShowDatePicker() {
        DatePickerDialog dlg = new DatePickerDialog(this, myDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dlg.show();
    }

    private void btn_AddNewTraining_onClick(View v) {
        String dateString = textDate.getText().toString();
        String startString = textTimeFrom.getText().toString();
        String endString = textTimeTo.getText().toString();

        if(dateString.isEmpty()|| startString.isEmpty() || endString.isEmpty())
            return;

        String groupId = dropDownGroups.getSelectedItem().toString();
        String trainingName = service.getNameForGroupId(groupId);

        try{
            Date datum = new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
            Date start = new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(dateString + " " + startString);
            Date end = new SimpleDateFormat("dd.MM.yyyy hh:mm").parse(dateString + " " + endString);

            Training t = new Training(0, trainingName, datum, start, end, textDescription.getText().toString(), 0);
            service.addTraining(groupId, t);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();

        }catch(Exception e){}
    }

    private void btn_Abort_onClick(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
