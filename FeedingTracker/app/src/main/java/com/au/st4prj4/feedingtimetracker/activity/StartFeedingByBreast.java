package com.au.st4prj4.feedingtimetracker.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.R;
import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.au.st4prj4.feedingtimetracker.viewmodels.LoginViewModel;
import com.au.st4prj4.feedingtimetracker.viewmodels.StartFeedingByBreastView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//this class i simulated to show what happens if the class were to receive data.
public class StartFeedingByBreast extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    //private FirebaseFirestore db;
    private StartFeedingByBreastView viewModel; //viewModel
    //String userID;
    ProgressBar progressBar;
    Button done;
    ImageView logo;
    TextView breastFeedingInfo;
    Feeding feeding = new Feeding();
    int dataSavedSucces =0;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_feeding_by_breast);
        viewModel = new ViewModelProvider(this).get(StartFeedingByBreastView.class);
        setUp();
        //generate some demo data
        generateDemoData();

        done.setOnClickListener(view -> {
            saveData(feeding);
        });

        logo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finalSetup();
        }
});
    }

    private void finalSetup() {

        breastFeedingInfo.setText("Data received. You can press done to save and return");
        progressBar.setVisibility(View.GONE);
        done.setVisibility(View.VISIBLE);
    }

    //this method is purely for demo purposes
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateDemoData() {
        // creating an object of Random class
        Random random = new Random();
        // Generates random integers 0 to 49
        int min = 0;
        int max = 10;
        double x = random.nextDouble()*(max-min+1)+min;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String decimalConverter = decimalFormat.format(x);
        feeding.setMilkInMl(x);
        LocalDate dateTime;
        dateTime= LocalDate.now();
        feeding.setDate(dateTime.toString());
        //double random = ThreadLocalRandom.current().nextDouble(min, max);

    }



    private void saveData(Feeding feeding) {
        /*Map<String, Object> data = new HashMap<>();
        data.put("date", feeding.getDate());
        data.put("milk",feeding.getMilkInMl());

        DocumentReference df= db.collection("account").document(userID).collection("BreastFeeding").document();
        df.set(data).addOnCompleteListener(task1 -> {
            Log.d("FeedingByBottle:", "onSuccess: new feeding data saved for "+ userID);

        });*/
        dataSavedSucces=viewModel.saveMilkData(feeding);
        if(dataSavedSucces == 1){
            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StartFeedingByBreast.this, MainMenuActivity.class));
            finish();
        }else if(dataSavedSucces ==-1){
            //Toast.makeText(LoginActivity.this,"LogIn Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setUp() {

        // Initialize Firebase Auth
       // mAuth = FirebaseAuth.getInstance();
        //db = FirebaseFirestore.getInstance();
        //userID = mAuth.getCurrentUser().getUid();

        progressBar= findViewById(R.id.progressBar_breastFeeding);
        done = findViewById(R.id.breastFeedingDone_btn);
        breastFeedingInfo = findViewById(R.id.breastfeedingInfo_txtV);
        logo= findViewById(R.id.breastFeedingLogo_img);

    }
}