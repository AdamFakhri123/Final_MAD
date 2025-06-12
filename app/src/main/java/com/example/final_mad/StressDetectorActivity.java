package com.example.final_mad;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class StressDetectorActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView txtStatus;
    private boolean isTesting = false;
    private int shakeCount = 0;
    private float shakeThreshold = 15.0f; // change based on test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_detector);

        txtStatus = findViewById(R.id.txtStatus);
        Button btnStartTest = findViewById(R.id.btnStartTest);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        btnStartTest.setOnClickListener(v -> {
            if (accelerometer != null) {
                shakeCount = 0;
                txtStatus.setText("Testing... Shake the phone now!");
                isTesting = true;
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

                // Stop after 6 seconds
                new Handler().postDelayed(() -> {
                    isTesting = false;
                    sensorManager.unregisterListener(this);
                    displayStressLevel();
                }, 6000);
            } else {
                txtStatus.setText("Accelerometer not available.");
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isTesting) return;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

        if (acceleration > shakeThreshold) {
            shakeCount++;
        }
    }

    private void displayStressLevel() {
        String result;
        if (shakeCount < 3) {
            result = "🟢 Low Stress";
        } else if (shakeCount < 7) {
            result = "🟡 Moderate Stress";
        } else {
            result = "🔴 High Stress";
        }

        txtStatus.setText("Result: " + result + "\nShakes: " + shakeCount);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
