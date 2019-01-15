package com.example.bbischler.badminton.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.example.bbischler.badminton.Model.AcceptState;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.example.bbischler.badminton.Service.MySession;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    IBadmintonServiceInterface service;
    ArrayList<Training> trainings = new ArrayList<>();
    private trainingAdapter _trainingAdapter;
    private ListView listView;
    private String description;
    Menu menu;
    FloatingActionButton fab;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        setOptionsMenuForLoggedIn();


        return true;
    }

    private void setOptionsMenuForLoggedIn(){
        if(MySession.isUserLoggedIn() && menu != null){
            MenuItem loginMenuItem = menu.findItem(R.id.trainerLogin);
            loginMenuItem.setTitle("Logout");

            MenuItem groupaddMenuItem = menu.findItem(R.id.groupAdd);
            groupaddMenuItem.setVisible(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_onClick();
            }
        });

        service = new MockBadmintonService();

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void fab_onClick() {
        Intent i = new Intent(this, NewTrainingPopup.class);
        startActivityForResult(i, 2);
    }

    private void refreshTrainings() {
        listView = (ListView) findViewById(R.id.trainingList);
        getTrainings();

        _trainingAdapter = new trainingAdapter(this, trainings);
        listView.setAdapter(_trainingAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                Training selectedTraining = (Training) arg0.getItemAtPosition(pos);
                if(selectedTraining.isCancelled())
                    return;
                Log.d("############", "Items " + selectedTraining.getName());
                Intent intent = new Intent(MainActivity.this, DetailedTrainingActivity.class);
                Bundle b = new Bundle();
                b.putInt("trainingID", selectedTraining.getId());
                intent.putExtras(b);
                startActivityForResult(intent, 1);
            }

        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Integer TrainingsId = data.getIntExtra("TrainingsId", -1);
                String action = data.getStringExtra("action");
                String userMode = data.getStringExtra("userMode");
                Training obj = null;

                if(TrainingsId == -1)
                    return;

                for(Training t : trainings)
                    if(t.getId() == TrainingsId){
                        obj = t;
                        break;
                    }


                if(action.equals("cancel")){
                    if(userMode.equals("trainer"))
                        obj.setCancelled(true);
                    else
                        obj.setAcceptState(AcceptState.Cancelled);
                    _trainingAdapter.notifyDataSetChanged();
                }

                if(action.equals("accept")){
                    obj.setAcceptState(AcceptState.Accepted);
                    _trainingAdapter.notifyDataSetChanged();
                }
            }
        }
        else if (requestCode == 2){
            if(resultCode == RESULT_OK)
                refreshTrainings();
        }
    }

    private void getTrainings() {

        ArrayList<String> myCodes;
        Hashtable<String, Boolean> myCancellations;

        if(MySession.isUserLoggedIn()){
            myCodes = MySession.loggedInUser.getGroups();
            myCancellations = new Hashtable<>();
        }
        else{
            Gson gson = new Gson();

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
            String json = pref.getString("myCodes", "");
            myCodes = (json == "") ? new ArrayList<String>() : gson.fromJson(json, ArrayList.class);

            json = pref.getString("myCancellations", "");
            myCancellations = (json == "") ? new Hashtable<String, Boolean>() : gson.fromJson(json, Hashtable.class);
        }


        trainings.clear();
        for(String code : myCodes){
            ArrayList<Training> tmptrainings = service.getTrainingsForGroup(code);
            for(Training t : tmptrainings){
                if(myCancellations.containsKey(t.getId().toString())){
                    t.setAcceptState(myCancellations.get(t.getId().toString()) ? AcceptState.Accepted : AcceptState.Cancelled);
                }
                else{
                    t.setAcceptState(AcceptState.Unset);
                }
            }

            trainings.addAll(tmptrainings);
        }

        trainings.sort(new Comparator<Training>() {
            @Override
            public int compare(Training t1, Training t2) {
                return t1.getRealDate().compareTo(t2.getRealDate());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        setOptionsMenuForLoggedIn();
        if(MySession.isUserLoggedIn())
            fab.setVisibility(View.VISIBLE);
        else
            fab.setVisibility(View.GONE);


        refreshTrainings();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.groupAdd:
                Intent intent = new Intent(this, GroupActivity.class);
                intent.putExtra("isNavigatable", true);
                startActivity(intent);
                return true;
            case R.id.trainerLogin:
                if(MySession.isUserLoggedIn())
                    logout();
                else
                    showLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showLogin() {
        Intent intent = new Intent(this, TrainerLoginActivity.class);
        startActivity(intent);
    }

    private void logout() {
        MySession.loggedInUser = null;
        MenuItem loginMenuItem = menu.findItem(R.id.trainerLogin);
        loginMenuItem.setTitle("Trainer Login");
        MenuItem groupaddMenuItem = menu.findItem(R.id.groupAdd);
        groupaddMenuItem.setVisible(true);
        fab.setVisibility(View.GONE);

        refreshTrainings();
    }
}
