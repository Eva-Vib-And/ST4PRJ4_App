package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;
import com.google.firebase.auth.FirebaseUser;


public class RegisterViewModel extends AndroidViewModel {
    public Repository repository;
    private FirebaseUser currentUser;
    int returnNumber;

    public RegisterViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        currentUser= repository.getCurrentUserOfApp();
    }

    public FirebaseUser getCurrentUser(){
        return currentUser;
    }
    public int createUser(String email, String pw, Context context){
        returnNumber = repository.CreateUserUsingEmailAndPW(email,pw, context);
        return returnNumber;
    }
    public void saveUserDetails(String name, String email){
        repository.saveUserDetails(name,email);
    }

}
