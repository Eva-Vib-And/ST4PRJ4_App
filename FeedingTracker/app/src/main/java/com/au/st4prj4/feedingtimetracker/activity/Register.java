package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.au.st4prj4.feedingtimetracker.R;
import com.au.st4prj4.feedingtimetracker.viewmodels.RegisterViewModel;


public class Register extends AppCompatActivity {

    private EditText email,password,fullName;
    private Button registerBtn;
    private ProgressBar progressBar;
    String userID;

    private RegisterViewModel viewModel; //viewModel
    int CreateUserSucces =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //setting up the viewModel so we can get data from repository through here.
        viewModel = new ViewModelProvider(Register.this).get(RegisterViewModel.class);
        userID = viewModel.getCurrentUser();


        email = findViewById(R.id.newEmailInput_edit);
        password = findViewById(R.id.newPasswordInput_edit);
        fullName = findViewById(R.id.fullName);
        registerBtn = findViewById(R.id.registorUser_btn);
        progressBar = findViewById(R.id.progressBar2);


        if(userID!=null){
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
            CreateUserSucces= viewModel.createUser(emailInput, passwordInput);
            if(CreateUserSucces == 1){
                saveUserDetail();
                startActivity(new Intent(Register.this, MainActivity.class));
                finish();
            }else if(CreateUserSucces ==-1){
                //Toast.makeText(LoginActivity.this,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
            //old save user funktion
            /*mAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    saveUserDetail();
                    Toast.makeText(Register.this,"User registered successfully",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Register.this,MainMenuActivity.class));
                }else{
                    Toast.makeText(Register.this,"Registration Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(GONE);
                }
            });*/
        }
    }

    private void saveUserDetail() {
        viewModel.saveUserDetails(fullName.getText().toString(),email.getText().toString());
        //old save user code
      /*  userID = mAuth.getCurrentUser().getUid();
        // Create a new user with a first name and email
        Map<String, Object> user = new HashMap<>();
        user.put("fullName", fullName.getText().toString());
        user.put("email",email.getText().toString());

        DocumentReference df= db.collection("account").document(userID);

       df.set(user).addOnCompleteListener(task1 -> {
            Log.d("Register:", "onSuccess: new user saved for "+ userID);
        });*/
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