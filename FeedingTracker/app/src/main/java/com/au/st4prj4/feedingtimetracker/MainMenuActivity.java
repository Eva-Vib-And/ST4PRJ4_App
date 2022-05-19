package com.au.st4prj4.feedingtimetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainMenuActivity extends AppCompatActivity {
    private TextView txt_option;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        //buttons setup
        Button startButton = (Button) (findViewById(R.id.startButton));
        Button overviewButton = (Button) (findViewById(R.id.overviewButton));
        Button milkplanButton = (Button) (findViewById(R.id.milkplanButton));
        //menu setup
        txt_option = findViewById(R.id.txt_option);

        //fetch users data
        getUserData();

        //buttons listener
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

        //popup menu listener
        txt_option.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, txt_option);
            popupMenu.inflate(R.menu.option_menu);


            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.logout:
                        mAuth.signOut();
                        startActivity(new Intent(this, LoginActivity.class));
                        finish();
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    private void getUserData() {

    }
}