package com.au.st4prj4.feedingtimetracker.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.au.st4prj4.feedingtimetracker.MilkPlanActivity;
import com.au.st4prj4.feedingtimetracker.OverviewAcitivity;
import com.au.st4prj4.feedingtimetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainMenuActivity extends AppCompatActivity {
    private TextView txt_option, personal_welcome;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private ImageView start, overview;
    String userID;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        //buttons setup
       // Button startButton = (Button) (findViewById(R.id.startButton));
       // Button overviewButton = (Button) (findViewById(R.id.overviewButton));
        Button milkplanButton = (Button) (findViewById(R.id.milkplanButton));
        //menu setup
        txt_option = findViewById(R.id.txt_option);
        //general setup
        personal_welcome = findViewById(R.id.headlineText);
        start = findViewById(R.id.start_img);
        overview = findViewById(R.id.overview_img);
        //fetch users data
        getUserData();

        //listener
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent-objekt indeholder en destination - vi vil gå fra den ene aktivitet til den anden
                Intent intent = new Intent(MainMenuActivity.this, ChooseFeedingType.class);
                startActivity(intent);
            }
        });
        overview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                 public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.logout:
                            mAuth.signOut();
                            startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
                            finish();
                            break;
                    }
                 return false;
             }
        });
            popupMenu.show();
        });
    }

    private void getUserData() {
        DocumentReference df = db.collection("account").document(userID);
        df.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                personal_welcome.setText(String.format(getResources().getString(R.string.WelcomeNameMenu_txtv)+" "+value.getString("fullName")));
            }
        });
    }
}