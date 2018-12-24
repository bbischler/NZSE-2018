package com.example.bbischler.badminton.Group;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bbischler.badminton.Main.MainActivity;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {

    IBadmintonServiceInterface service;
    boolean backButtonEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        service = new MockBadmintonService();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras.getBoolean("isNavigatable")){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            backButtonEnabled = false;
        }

        Button btn_Connect = findViewById(R.id.btn_Connect);
        btn_Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Connect_onClick(v);
            }
        });
    }

    public void btn_Connect_onClick(View view) {
        EditText txt_Code = findViewById(R.id.txt_Code);
        String groupCode = txt_Code.getText().toString();
        service.connectToGroup(groupCode);

        ArrayList<String> myCodes;
        Gson gson = new Gson();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = pref.edit();
        String json = pref.getString("myCodes", "");
        myCodes = (json == "") ? new ArrayList<String>() : gson.fromJson(json, ArrayList.class);

        myCodes.add(groupCode);
        json = gson.toJson(myCodes);
        prefsEditor.putString("myCodes", json);
        prefsEditor.commit();

        returnToOverview();
    }

    private void returnToOverview() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
