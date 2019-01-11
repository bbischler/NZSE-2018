package com.example.bbischler.badminton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bbischler.badminton.Group.GroupActivity;
import com.example.bbischler.badminton.Main.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);

        ArrayList<String> myCodes;
        Gson gson = new Gson();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.clear();
//        editor.commit();
        String json = pref.getString("myCodes", "");
        myCodes = (json == "") ? new ArrayList<String>() : gson.fromJson(json, ArrayList.class);

        // determine where to go
        if (myCodes.size() > 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, GroupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("isNavigatable", false);
            finish();
            startActivity(intent);
        }
    }

}