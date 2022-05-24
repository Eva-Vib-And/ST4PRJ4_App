package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;

public class LoginViewModel extends ViewModel{
    public Repository repository;
    String currentUser;

    public LoginViewModel(@NonNull Application application) {
        this.repository = Repository.getInstance(application);
        currentUser= repository.getUserID();
    }

    public LoginViewModel() {
    }

    public String getCurrentUser(){
        return currentUser;
    }
    public int signIn(String email, String pw){
    return repository.signInUsingEmailAndPW(email,pw);
    }

}
