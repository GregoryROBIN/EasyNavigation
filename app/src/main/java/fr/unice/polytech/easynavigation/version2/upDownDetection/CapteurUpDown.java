package fr.unice.polytech.easynavigation.version2.upDownDetection;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.util.Map;
import java.util.Observable;

/**
 * Created by chapon on 13/10/16.
 */

public class CapteurUpDown extends Observable implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    Context context;
    Display display;
    float[] donnees = new float[2];
    float[] history = new float[2];
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    private Sensor mGyroscope;
    private boolean running = false;


    public CapteurUpDown(Context c) {
        this.context = c;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();

    }


    public void stop() {
        mSensorManager.unregisterListener(this);
        running = false;
    }

    public void start() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_UI);
        running = true;

        Log.d("Accelerometer","upDown and validate");
    }

    public boolean isRunning() {
        return running;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                setChanged();
                notifyObservers("valid");
            }else{
                switch (display.getRotation()) {
                    case Surface.ROTATION_0:
                        donnees[0] = event.values[0];
                        donnees[1] = event.values[1];
                        break;
                    case Surface.ROTATION_90:
                        donnees[0] = -event.values[1];
                        donnees[1]= event.values[0];
                        break;
                    case Surface.ROTATION_180:
                        donnees[0] = -event.values[0];
                        donnees[1]= -event.values[1];
                        break;
                    case Surface.ROTATION_270:
                        donnees[0] = event.values[1];
                        donnees[1] = -event.values[0];
                        break;
                }
                float diferenceY = Math.abs(history[1]-donnees[1]);
                if(diferenceY >2){
                    setChanged();
                    notifyObservers("descendre");
                }
                history[0]= donnees[0];
                history[1]=donnees[1];

            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}