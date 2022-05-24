package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.au.st4prj4.feedingtimetracker.R;
import com.au.st4prj4.feedingtimetracker.viewmodels.MainViewModel;
import com.google.firebase.auth.FirebaseUser;

//This app i made mainly by the knowlogde from Aarhus University course SWMAD-01 in fall 2021 and Android developer: https://developer.android.com/

public class MainActivity extends AppCompatActivity {
    //fields & widgets
    private Button signin_btn;
    private MainViewModel viewModel; //viewModel
    private FirebaseUser userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the viewModel so we can get data from repository through here.
        viewModel = new ViewModelProvider(MainActivity.this).get(MainViewModel.class);
        userID = viewModel.getCurrentUser();

        //button listener
        signin_btn = findViewById(R.id.signIn_btn);
        signin_btn.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

       // checking if user is already login
        if(userID !=null){
            startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
        }

    }

}