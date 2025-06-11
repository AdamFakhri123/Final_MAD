package com.example.final_mad;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnMoodTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoodTracker = findViewById(R.id.btnMoodTracker);

        btnMoodTracker.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MoodTrackerActivity.class);
            startActivity(intent);
        });
    }
}