package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;

public class MainMenuViewModel extends ViewModel {
    public Repository repository;
    String currentUser;
    String userName;

    public MainMenuViewModel(@NonNull Application application) {
        this.repository = Repository.getInstance(application);
        currentUser= repository.getUserID();

    }

    public MainMenuViewModel() {
    }

    public String getCurrentUser(){

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
