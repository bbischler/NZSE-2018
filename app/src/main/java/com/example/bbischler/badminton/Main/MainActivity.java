package com.example.bbischler.badminton.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bbischler.badminton.Details.DetailedTrainingActivity;
import com.example.bbischler.badminton.Group.GroupActivity;
import com.example.bbischler.badminton.Login.TrainerLoginActivity;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    IBadmintonServiceInterface service;
    ArrayList<Training> training = new ArrayList<>();
    private trainingAdapter _trainingAdapter;
    private ListView listView;
    private String description;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new MockBadmintonService();

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.trainingList);
        getTrainings();



/*
        training.add(new Training(1, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description, 14));
        training.add(new Training(2, "Ligamannschaft", new Date(), new Date(), new Date(), description,13));
        training.add(new Training(3, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description,12));
        training.add(new Training(4, "Ligamannschaft", new Date(), new Date(), new Date(), description,20));

*/

        _trainingAdapter = new trainingAdapter(this, training);
        listView.setAdapter(_trainingAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                Training selectedTraining = (Training) arg0.getItemAtPosition(pos);
                Log.d("############", "Items " + selectedTraining.getName());
                Intent intent = new Intent(MainActivity.this, DetailedTrainingActivity.class);
                Bundle b = new Bundle();
                b.putInt("trainingID", selectedTraining.getId());
                intent.putExtras(b);
                startActivity(intent);
            }

        });
    }

    private void getTrainings() {
        ArrayList<String> myCodes;
        Gson gson = new Gson();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String json = pref.getString("myCodes", "");
        myCodes = (json == "") ? new ArrayList<String>() : gson.fromJson(json, ArrayList.class);

        for(String code : myCodes){
            training.addAll(service.getTrainingsForGroup(code));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.groupAdd:
                intent = new Intent(this, GroupActivity.class);
                intent.putExtra("isNavigatable", true);
                startActivity(intent);
                return true;
            case R.id.trainerLogin:
                intent = new Intent(this, TrainerLoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
