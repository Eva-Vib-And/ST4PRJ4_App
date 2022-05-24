package com.au.st4prj4.feedingtimetracker.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.R;
import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StartFeedingByBottle extends AppCompatActivity {
    Feeding feeding = new Feeding();
    Button back_Btn, save_Btn;
    TextView infoText_txtV;
    EditText totalBottleIntake_txtV;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    String userID;


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_feeding_by_bottle);

        setUp();

        back_Btn.setOnClickListener(view -> {
            finish();
        });
        save_Btn.setOnClickListener(view -> {

            //add data to database
            String milk = totalBottleIntake_txtV.getText().toString();

            feeding.setMilkInMl(Double.parseDouble(milk)); //tjek om dette virker
            LocalDate dateTime;
            dateTime= LocalDate.now();
            feeding.setDate(dateTime.toString());

            saveData(feeding);
            startActivity(new Intent(this, MainMenuActivity.class));
        });
    }

    private void saveData(Feeding feeding) {
        Map<String, Object> data = new HashMap<>();
        data.put("date", feeding.getDate());
        data.put("milk",feeding.getMilkInMl());

        DocumentReference df= db.collection("account").document(userID).collection("BottleFeeding").document();
        df.set(data).addOnCompleteListener(task1 -> {
            Log.d("FeedingByBottle:", "onSuccess: new feeding data saved for "+ userID);
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void setUp() {
        back_Btn = findViewById(R.id.backToChooseFeeding_btn);
        save_Btn = findViewById(R.id.done_btn_bottleFeeding);

        infoText_txtV = findViewById(R.id.feedingByBottleLabel_txtV);
        totalBottleIntake_txtV = findViewById(R.id.totalBottleInTake_txtV);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
    }

}