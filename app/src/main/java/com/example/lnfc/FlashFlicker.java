package com.example.lnfc;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

public class FlashFlicker extends Thread {
    private CameraManager cameraManager;
    private String pattern;
    private int pulseWidth;

    private String TAG = "pulseLog";

    FlashFlicker(CameraManager cameraManager, String pattern, int frequency) {
        this.cameraManager = cameraManager;
        pulseWidth = 500000 / frequency;
        this.pattern = pattern;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void patternFlashFlicker() {

        char[] patternArr = pattern.toCharArray();

        for (char x : patternArr) {
            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                if (x == '1') {
                    cameraManager.setTorchMode(cameraId, true);
                    Log.d(TAG, "1");
//                    Thread.sleep(500/frequency);
                    TimeUnit.MICROSECONDS.sleep(pulseWidth);
                } else {
                    cameraManager.setTorchMode(cameraId, false);
                    Log.d(TAG, "0");
//                    Thread.sleep(500/frequency);
                    TimeUnit.MICROSECONDS.sleep(pulseWidth);
                }

            } catch (CameraAccessException ignored) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void run() {
        patternFlashFlicker();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashLightFlicker(int f) {
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            if (f == 0) {
                cameraManager.setTorchMode(cameraId, true);
            } else {
                while (true) {
                    cameraManager.setTorchMode(cameraId, true);
                    TimeUnit.MICROSECONDS.sleep(pulseWidth);
                    cameraManager.setTorchMode(cameraId, false);
                    TimeUnit.MICROSECONDS.sleep(pulseWidth);
                }
            }

        } catch (CameraAccessException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
