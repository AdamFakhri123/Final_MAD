package com.example.final_mad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SOS_Call extends AppCompatActivity {

    private Button buttonCall, buttonChill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sos_help);

        buttonCall = findViewById(R.id.button);
        buttonChill = findViewById(R.id.button2);

        // Call button
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "+60-22-222-222";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

//        // Go to Chill Space
//        buttonChill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SOS_Call.this, Chill_Space.class);
//                startActivity(intent);
//            }
//        });
    }
}