package com.au.st4prj4.feedingtimetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseFeedingType extends AppCompatActivity {
    private String userChooseFeedingType =null;
    Button nextActivity_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feeding_type);

        nextActivity_btn = findViewById(R.id.next_btn);
        nextActivity_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(this, EditFeedingData.class);
                startActivity(intent);
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