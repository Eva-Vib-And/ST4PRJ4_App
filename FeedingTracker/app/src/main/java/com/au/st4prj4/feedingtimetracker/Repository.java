package com.au.st4prj4.feedingtimetracker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.au.st4prj4.feedingtimetracker.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


public class Repository {

    private static Repository instance; //for singleton pattern
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    Context context;
    int onSucces =0;
    String userID;
    String userName;

    //Singleton pattern to make only one instance of the Repository
    public static Repository getInstance(Application app) {

        if (instance == null) {
            instance = new Repository(app);
        }
        return instance;
    }
    public Repository(Application app) {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        context = app.getApplicationContext();
    }

    public String getUserID(){
        userID = mAuth.getCurrentUser().getUid();
        return userID;
    }

    public int signInUsingEmailAndPW(String emailInput, String passwordInput){

        mAuth.signInWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(context,"Login successfully",Toast.LENGTH_SHORT).show();
               // context.startActivity(new Intent(context, MainActivity.class));
                onSucces = 1;
                //startActivity(new Intent(context, MainActivity.class));
            }else{
                Toast.makeText(context,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                onSucces =-1;
            }
        });

        return onSucces;
    }
    public int CreateUserUsingEmailAndPW(String emailInput, String passwordInput){

        mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(context,"User registered successfully",Toast.LENGTH_SHORT).show();
                // context.startActivity(new Intent(context, MainActivity.class));
                onSucces = 1;
                //startActivity(new Intent(context, MainActivity.class));
            }else{
                Toast.makeText(context,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                onSucces =-1;
            }
        });

        return onSucces;
    }
    public void saveUserDetails(String fullName, String email){
        userID = mAuth.getCurrentUser().getUid();
        // Create a new user with a first name and email
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", fullName);
        user.put("email",email);

        DocumentReference df= db.collection("account").document(userID);

        df.set(user).addOnCompleteListener(task1 -> {
            Log.d("Register:", "onSuccess: new user saved for "+ userID);
        });

    }
    public String getUserName(){
        //String userName;
        DocumentReference df = db.collection("account").document(userID);
        df.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 userName = value.getString("fullName");
                //personal_welcome.setText(String.format(getResources().getString(R.string.WelcomeNameMenu_txtv)+" "+value.getString("fullName")));
            }
        });
        return userName;
    }
    public void SignOut(){
        mAuth.signOut();
    }
}

