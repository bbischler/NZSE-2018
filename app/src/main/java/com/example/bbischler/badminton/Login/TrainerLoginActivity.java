package com.example.bbischler.badminton.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bbischler.badminton.Main.MainActivity;
import com.example.bbischler.badminton.Model.User;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.example.bbischler.badminton.Service.MySession;

import org.w3c.dom.Text;

public class TrainerLoginActivity extends AppCompatActivity {

    IBadmintonServiceInterface service;
    EditText txt_email;
    EditText txt_password;
    CoordinatorLayout rootlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        service = new MockBadmintonService();

        Button btn_signIn = findViewById(R.id.email_sign_in_button);
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);
        rootlayout = findViewById(R.id.root);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_signIn_onClick(v);
            }
        });
    }

    private void btn_signIn_onClick(View v) {
        String email = txt_email.getText().toString();
        String password = txt_password.getText().toString();

        if(email.equals("") || password.equals("")){
            Toast.makeText(this, "Email und Passwort angeben!", Toast.LENGTH_LONG).show();
            return;
        }

        User trainer = service.login(email, password);
        if(trainer == null){
            Toast.makeText(this, "Login fehlgeschlagen!", Toast.LENGTH_LONG).show();
            return;
        }

        MySession.loggedInUser = trainer;
        onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
