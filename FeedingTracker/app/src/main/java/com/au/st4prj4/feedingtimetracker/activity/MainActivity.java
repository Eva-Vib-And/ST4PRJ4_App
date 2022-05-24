package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.au.st4prj4.feedingtimetracker.R;
import com.au.st4prj4.feedingtimetracker.viewmodels.MainViewmodel;
import com.google.firebase.auth.FirebaseAuth;

//This app i made mainly by the knowlogde from Aarhus University course SWMAD-01 in fall 2021 and Android developer: https://developer.android.com/

public class MainActivity extends AppCompatActivity {
    private Button signin_btn;
    private MainViewmodel viewModel; //viewModel
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting up the viewModel so we can get data from repository through here.
        viewModel = new ViewModelProvider(this).get(MainViewmodel.class);
        userID = viewModel.getCurrentUser();



        signin_btn = findViewById(R.id.signIn_btn);
        signin_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });


       // FirebaseUser currentUser = mAuth.getCurrentUser();
        if(userID !=null){
            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
        }

    }
}