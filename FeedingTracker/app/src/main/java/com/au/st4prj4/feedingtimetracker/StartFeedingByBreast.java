package com.au.st4prj4.feedingtimetracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.au.st4prj4.feedingtimetracker.models.FeedingList;

import java.time.LocalDate;
import java.util.ArrayList;

public class StartFeedingByBreast extends AppCompatActivity {
Feeding feeding;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_feeding_data);

        feeding = new Feeding();
        //generate some data

        feeding.setFeedingType("Breast");
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
        intent.putExtra("data", 10);
        finish();
    }
}