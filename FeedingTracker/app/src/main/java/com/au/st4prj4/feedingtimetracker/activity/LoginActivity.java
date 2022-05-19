package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView register;
    private Button login;
    private EditText email,password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //Setup
        email = findViewById(R.id.emailInput_edit);
        password = findViewById(R.id.passwordInput_edit);
        register = findViewById(R.id.register_txtV);
        progressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login_btn);

        //button listener
        login.setOnClickListener(view -> loginUser());

        register.setOnClickListener(view -> {
            startActivity(new Intent(this, Register.class));
        });

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
            finish();
        }
    }
    private void loginUser() {
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();

        if(TextUtils.isEmpty(emailInput)){
            email.setError("Email missing");
            email.requestFocus();
        }else if(TextUtils.isEmpty(passwordInput)){
            password.setError("password is missing or not correct setup");
            password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            //sign in
            mAuth.signInWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        }

    }
}