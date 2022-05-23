package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.au.st4prj4.feedingtimetracker.R;
import com.google.firebase.auth.FirebaseAuth;

//This app i made mainly by the knowlogde from Aarhus University course SWMAD-01 in fall 2021 and Android developer: https://developer.android.com/

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button signin_btn;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signin_btn = findViewById(R.id.signIn_btn);
        signin_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });


       // FirebaseUser currentUser = mAuth.getCurrentUser();
        //userID = mAuth.getCurrentUser();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
        }

    }
}