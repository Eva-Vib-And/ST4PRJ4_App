package com.au.st4prj4.feedingtimetracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.au.st4prj4.feedingtimetracker.R;

public class ChooseFeedingType extends AppCompatActivity {
    private String userChooseFeedingType =null;
    Button nextActivity_btn;

    /*ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
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
    });*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feeding_type);

        nextActivity_btn = findViewById(R.id.next_btn);
        nextActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseFeedingType.this, StartFeedingByBottle.class); //need to go to correct activity
                Intent intent2 = new Intent(ChooseFeedingType.this, StartFeedingByBreast.class);
                //startActivity(intent);
                /*if (userChooseFeedingType.contains("Breast")){
                    activityResultLauncher.launch(intent2);
                }else { activityResultLauncher.launch(intent);}*/
                if(userChooseFeedingType=="Breast"){
                startActivity(intent2);
                }else{
                    startActivity(intent);
                }
            }
        });

    }

    public void userChoseFeedingType(View view) {
        RadioGroup radioGrup = findViewById(R.id.RadioGruop);
        switch (radioGrup.getCheckedRadioButtonId()){
            case R.id.Breast:
                Toast.makeText(this,"right breast chosen",Toast.LENGTH_SHORT).show();
                userChooseFeedingType ="Breast";
                break;

            case R.id.bottle:
                Toast.makeText(this,"bottle chosen",Toast.LENGTH_SHORT).show();
                userChooseFeedingType = "Bottle";
                break;
        }

    }
}