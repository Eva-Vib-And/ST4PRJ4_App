package com.au.st4prj4.feedingtimetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseFeedingType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feeding_type);


    }

    public void userChoseFeedingType(View view) {
        RadioGroup radioGrup = findViewById(R.id.RadioGruop);
        switch (radioGrup.getCheckedRadioButtonId()){
            case R.id.rightBreast:
                Toast.makeText(this,"right breast chosen",Toast.LENGTH_SHORT).show();
                break;

            case R.id.leftBreast:
                Toast.makeText(this,"left breast chosen",Toast.LENGTH_SHORT).show();
                break;

        }

    }
}