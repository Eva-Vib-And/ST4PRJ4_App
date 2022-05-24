package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;
import com.google.firebase.auth.FirebaseUser;

public class MainMenuViewModel extends AndroidViewModel {
    public Repository repository;
    FirebaseUser currentUser;
    String userName;

    public MainMenuViewModel(@NonNull Application application) {
        super(application);
        this.repository = Repository.getInstance(application);
        currentUser= repository.getCurrentUserOfApp();

    }

    public FirebaseUser getCurrentUser(){

        return currentUser;
    }
    public String getUserName(){
         userName = repository.getUserName();
        return userName;
    }
    public void signUserOut(){
        repository.SignOut();
    }
}
