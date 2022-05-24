package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;


public class RegisterViewModel extends ViewModel {
    public Repository repository;
    String currentUser;
    int returnNumber;

    public RegisterViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        currentUser= repository.getUserID();
    }
    public RegisterViewModel(){

    }

    public String getCurrentUser(){
        return currentUser;
    }
    public int createUser(String email, String pw){
        returnNumber= repository.CreateUserUsingEmailAndPW(email,pw);
        return returnNumber;
    }
    public void saveUserDetails(String name, String email){
        repository.saveUserDetails(name,email);
    }

}
