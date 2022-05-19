package com.au.st4prj4.feedingtimetracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StartFeedingByBreast extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    String userID;
    ProgressBar progressBar;
    Button done;
    TextView breastFeedingInfo;
    Feeding feeding = new Feeding();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_feeding_by_breast);
        setUp();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //generate some demo data
        generateDemoData();
        done.setOnClickListener(view -> {
            saveData(feeding);
        });
    }
    //this method is purely for demo purposes
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateDemoData() {
        // creating an object of Random class
        Random random = new Random();
        // Generates random integers 0 to 49
        int min = 0;
        int max = 20;
        double x = random.nextDouble()*(max-min+1)+min;
        feeding.setMilkInMl(x);
        LocalDate dateTime;
        dateTime= LocalDate.now();
        feeding.setDate(dateTime.toString());
        //double random = ThreadLocalRandom.current().nextDouble(min, max);
        breastFeedingInfo.setText("Data received. You can press done to save and return");
        progressBar.setVisibility(View.GONE);

    }

    private void saveData(Feeding feeding) {
        Map<String, Object> data = new HashMap<>();
        data.put("date", feeding.getDate());
        data.put("milk",feeding.getMilkInMl());

        DocumentReference df= db.collection("account").document(userID).collection("BreastFeeding").document();
        df.set(data).addOnCompleteListener(task1 -> {
            Log.d("FeedingByBottle:", "onSuccess: new feeding data saved for "+ userID);
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
        });
    }

    private void setUp() {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        progressBar= findViewById(R.id.progressBar_breastFeeding);
        done = findViewById(R.id.breastFeedingDone_btn);
        breastFeedingInfo = findViewById(R.id.breastfeedingInfo_txtV);
    }
}