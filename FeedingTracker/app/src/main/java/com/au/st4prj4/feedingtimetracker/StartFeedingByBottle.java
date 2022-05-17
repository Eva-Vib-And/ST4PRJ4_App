package com.au.st4prj4.feedingtimetracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartFeedingByBottle extends AppCompatActivity {
    Button back_Btn, done_Btn,edit_Btn;
    TextView infoText_txtV, totalBottleIntake_txtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_feeding_by_bottle);

        setUp();

        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent();
                setResult(99,backIntent);
                finish();
            }
        });
        done_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                setResult(90,intent);
                finish();
            }
        });
        edit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //skal tilføje noget til en liste.
            }
        });
    }

    private void setUp() {
        back_Btn = findViewById(R.id.backToChooseFeeding_btn);
        done_Btn = findViewById(R.id.done_btn_bottleFeeding);
        edit_Btn = findViewById(R.id.editBottleIntake_btn);

        infoText_txtV = findViewById(R.id.bottleInfo_txtV);
        totalBottleIntake_txtV = findViewById(R.id.totalBottleInTake_txtV);
    }

}