package com.au.st4prj4.feedingtimetracker.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.au.st4prj4.feedingtimetracker.Repository;
import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.google.firebase.auth.FirebaseUser;

public class StartFeedingByBreastView extends AndroidViewModel {

    public Repository repository;
    private FirebaseUser currentUser;
    int returnNumber;

    public StartFeedingByBreastView(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application);
        currentUser= repository.getCurrentUserOfApp();
    }

    public int saveMilkData(Feeding feeding){
        returnNumber = repository.saveBreastMilkData(feeding);
        return returnNumber;
    }
}
