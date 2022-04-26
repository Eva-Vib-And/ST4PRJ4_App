package com.au.st4prj4.feedingtimetracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseFeedingType extends AppCompatActivity {
    private String userChooseFeedingType =null;
    Button nextActivity_btn;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.d(TAG, "onActivityResult: back from startFeedingByBottle class");
            if(result.getResultCode()==90){
                finish();
            } else if(result.getResultCode()==99){
                //stays in activity
                Log.d(TAG, "onActivityResult: back from startFeedingByBottle class by pressed back button");
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feeding_type);

        nextActivity_btn = findViewById(R.id.next_btn);
        nextActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseFeedingType.this, StartFeedingByBottle.class); //need to go to correct activity
                Intent intent2 = new Intent(ChooseFeedingType.this, StartFeedingByBottle.class);
                //startActivity(intent);
                if (userChooseFeedingType.contains("Breast")){
                    activityResultLauncher.launch(intent2);
                }else { activityResultLauncher.launch(intent);}
            }
        });

    }

    public void userChoseFeedingType(View view) {
        RadioGroup radioGrup = findViewById(R.id.RadioGruop);
        switch (radioGrup.getCheckedRadioButtonId()){
            case R.id.rightBreast:
                Toast.makeText(this,"right breast chosen",Toast.LENGTH_SHORT).show();
                userChooseFeedingType ="Right Breast";
                break;

            case R.id.leftBreast:
                Toast.makeText(this,"left breast chosen",Toast.LENGTH_SHORT).show();
                userChooseFeedingType = " Left Beast";
                break;

            case R.id.bottle:
                Toast.makeText(this,"bottle chosen",Toast.LENGTH_SHORT).show();
                userChooseFeedingType = "Bottle";
                break;
        }

    }
}