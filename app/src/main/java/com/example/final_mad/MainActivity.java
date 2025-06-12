package com.example.final_mad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnMoodTracker, btnPhysicalChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoodTracker = findViewById(R.id.btnMoodTracker);
        Button btnLightTracker = findViewById(R.id.btnLightTracker);
        Button btnStressDetector = findViewById(R.id.btnStressDetector);
        Button btnSOS = findViewById(R.id.btnSOS);
        Button btnChillSpace = findViewById(R.id.btnChillSpace);

        btnLightTracker.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LightTrackerActivity.class);
            startActivity(intent);
        });


        btnMoodTracker.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoodTrackerActivity.class);
            startActivity(intent);
        });



        btnStressDetector.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StressDetectorActivity.class);
            startActivity(intent);
        });

        btnSOS.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SOS_Call.class);
            startActivity(intent);
        });


        btnChillSpace.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChillSpaceActivity.class);
            startActivity(intent);
        });





    }
}
