package com.example.bbischler.badminton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Training> training = new ArrayList<>();
    private trainingAdapter _trainingAdapter;
    private ListView listView;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listView = (ListView) findViewById(R.id.trainingList);
        training.add(new Training(1, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description, 14));
        training.add(new Training(2, "Ligamannschaft", new Date(), new Date(), new Date(), description,13));
        training.add(new Training(3, "Anfänger Darmstadt", new Date(), new Date(), new Date(), description,12));
        training.add(new Training(4, "Ligamannschaft", new Date(), new Date(), new Date(), description,20));


        _trainingAdapter = new trainingAdapter(this, training);
        listView.setAdapter(_trainingAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                Training selectedTraining = (Training) arg0.getItemAtPosition(pos);
                Log.d("############", "Items " + selectedTraining.name);
                Intent intent = new Intent(MainActivity.this, DetailedTrainingActivity.class);
                Bundle b = new Bundle();
                b.putInt("trainingID", selectedTraining.id);
                intent.putExtras(b);
                startActivity(intent);
            }

        });
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
