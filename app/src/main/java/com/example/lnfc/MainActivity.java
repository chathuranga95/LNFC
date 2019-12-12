package com.example.lnfc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Sensor sensor;
    private SensorManager sensorManager;
    private String TAG = "lightTest";
    private EditText txtNum;
    private CameraManager cameraManager;
    private FlashFlicker flicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        txtNum = findViewById(R.id.txtNum);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    }

    private void startSensorRead() {
        if (sensor == null) {
            Log.d(TAG, "Sensor not found");
        } else {
            SensorEventListener sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    Log.d(TAG, Float.toString(sensorEvent.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };

            Log.d(TAG, "Started listening");
            Log.d(TAG, "Fastest frequency: " + SensorManager.SENSOR_DELAY_FASTEST);
            sensorManager.registerListener(sensorEventListener,
                    sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void startOp(View view) {

        String checkPattern = "";

        for (int i = 0; i < 50; i++) {
            checkPattern += "10";
        }

        FlashFlicker flicker = new FlashFlicker(cameraManager, checkPattern, 50);

//            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//            executorService.scheduleAtFixedRate(flicker, 0, 1, TimeUnit.MILLISECONDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flicker.patternFlashFlicker();
        }


    }

    public void sendInp(View view) {
        String testPattern = txtNum.getText().toString();
        FlashFlicker flicker = new FlashFlicker(cameraManager, testPattern, 50);

//            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//            executorService.scheduleAtFixedRate(flicker, 0, 1, TimeUnit.MILLISECONDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flicker.patternFlashFlicker();
        }
    }
}
