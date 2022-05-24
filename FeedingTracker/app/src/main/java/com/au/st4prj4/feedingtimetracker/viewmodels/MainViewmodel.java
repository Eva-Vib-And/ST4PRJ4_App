package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;

public class MainViewmodel extends ViewModel {
    public Repository repository;
    private String currentUser;

    public MainViewmodel(@NonNull Application application) {
        //super(application);
        this.repository = Repository.getInstance(application);
        currentUser = repository.getUserID();
    }
    public MainViewmodel (){

    }
    public String getCurrentUser(){
        return currentUser;
    }
}
