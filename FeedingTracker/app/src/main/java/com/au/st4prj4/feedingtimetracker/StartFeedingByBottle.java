package com.au.st4prj4.feedingtimetracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.au.st4prj4.feedingtimetracker.models.FeedingList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class StartFeedingByBottle extends AppCompatActivity {
    Feeding feeding = new Feeding();
    Button back_Btn, done_Btn;
    TextView infoText_txtV;
    EditText totalBottleIntake_txtV;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_feeding_by_bottle);

        setUp();

        back_Btn.setOnClickListener(view -> {
            Intent backIntent = new Intent();
            setResult(99,backIntent);
            finish();
        });
        done_Btn.setOnClickListener(view -> {

            //add data to model
            String milk = totalBottleIntake_txtV.getText().toString();
            feeding.setMilkInMl(Integer.parseInt(milk)); //tjek om dette virker
            feeding.setFeedingType("Bottle");
            LocalDate dateTime;
            dateTime= LocalDate.now();
            //add data to feeding list to keep track of total feedings
            FeedingList feedingList = new FeedingList();
            feedingList.setDate(dateTime);
           /* if(dateTime != feedingList.getDateTime()) {
                if(feedingList.getDateTime()==null) {
                    feedingList.setDate(dateTime);
                    //make new object
                }
            }*/
            feedingList.getTotaltFeedingsToday().add(feeding);

            Intent intent= new Intent();
            setResult(90,intent);
            finish();
        });
    }

    private void setUp() {
        back_Btn = findViewById(R.id.backToChooseFeeding_btn);
        done_Btn = findViewById(R.id.done_btn_bottleFeeding);

        infoText_txtV = findViewById(R.id.bottleInfo_txtV);
        totalBottleIntake_txtV = findViewById(R.id.totalBottleInTake_txtV);
    }

}