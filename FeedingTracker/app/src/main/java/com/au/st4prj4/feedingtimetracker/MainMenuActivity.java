package com.au.st4prj4.feedingtimetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button startButton = (Button) (findViewById(R.id.startButton));
        Button overviewButton = (Button) (findViewById(R.id.overviewButton));
        Button milkplanButton = (Button) (findViewById(R.id.milkplanButton));

    startButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //intent-objekt indeholder en destination - vi vil gå fra den ene aktivitet til den anden
            Intent intent = new Intent(MainMenuActivity.this, ChooseFeedingType.class);
            startActivity(intent);
        }
    });
    overviewButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //intent-objekt indeholder en destination - vi vil gå fra den ene aktivitet til den anden
            Intent intent = new Intent(MainMenuActivity.this, OverviewAcitivity.class);
            startActivity(intent);
        }
    });

    milkplanButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //intent-objekt indeholder en destination - vi vil gå fra den ene aktivitet til den anden
            Intent intent = new Intent(MainMenuActivity.this, MilkPlanActivity.class);
            startActivity(intent);
        }
    });

    }
}