package com.example.bbischler.badminton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Training> training = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listview = findViewById(R.id.trainingList);

        for (int i = 0; i < 20; i++) {
            training.add(new Training(i+7, "Training Nr. " + i));
        }

        ArrayAdapter<Training> adapter = new ArrayAdapter<Training>(this,
                android.R.layout.simple_list_item_1, training);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                Training selectedTraining = (Training)arg0.getItemAtPosition(pos);
                Log.d("############", "Items " + selectedTraining.name);
                Intent intent = new Intent(MainActivity.this, DetailedTrainingActivity.class);
                Bundle b = new Bundle();
                b.putInt("trainingID", selectedTraining.id);
                intent.putExtras(b);
                startActivity(intent);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
