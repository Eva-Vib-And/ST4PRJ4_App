package com.au.st4prj4.feedingtimetracker;

import static android.view.View.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email,password,fullName;
   // private TextView fullName;
    private Button registerBtn;
    private ProgressBar progressBar;
    String userID;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.newEmailInput_edit);
        password = findViewById(R.id.newPasswordInput_edit);
        fullName = findViewById(R.id.fullName);
        registerBtn = findViewById(R.id.registorUser_btn);
        progressBar = findViewById(R.id.progressBar2);
        //fullName = findViewById(R.id.fullName);

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(Register.this, MainMenuActivity.class));
            finish();
        }

        registerBtn.setOnClickListener(view -> createUser());
    }

    private void createUser() {
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();


        if(TextUtils.isEmpty(emailInput)){
            email.setError("Email missing");
            email.requestFocus();
        }else if(TextUtils.isEmpty(passwordInput)){
            password.setError("password is missing or not correct setup");
            password.requestFocus();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    saveUserDetail();
                    Toast.makeText(Register.this,"User registered successfully",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this,MainMenuActivity.class));
                }else{
                    Toast.makeText(Register.this,"Registration Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(GONE);
                }
            });
        }
    }

    private void saveUserDetail() {
        userID = mAuth.getCurrentUser().getUid();
        // Create a new user with a first name and email
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", fullName.getText().toString());
        user.put("email",email.getText().toString());

        DocumentReference df= db.collection("account").document(userID);

       df.set(user).addOnCompleteListener(task1 -> {
            Log.d("Register:", "onSuccess: new user saved for "+ userID);
        });
    }

  /*  // Initialize Firebase Auth
    mAuth = FirebaseAuth.getInstance();
    db = FirebaseFirestore.getInstance();

    logout = findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
        mAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    });*/
}