package fr.unice.polytech.easynavigation.version2.shacker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.util.Observable;

/**
 * Created by chapon on 13/10/16.
 */

public class CapteurShaker extends Observable implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 1.8F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    Context context;
    private long mShakeTimestamp;
    private int mShakeCount;
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    private boolean running = false;


    public CapteurShaker(Context c) {
        this.context = c;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    public void stop() {
        mSensorManager.unregisterListener(this);
        running = false;
    }

    public void start() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        running = true;
    }

    public boolean isRunning() {
        return running;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float gX = x / SensorManager.GRAVITY_EARTH;
        float gY = y / SensorManager.GRAVITY_EARTH;
        float gZ = z / SensorManager.GRAVITY_EARTH;

        // gForce will be close to 1 when there is no movement.
        double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            final long now = System.currentTimeMillis();
            // ignore shake events too close to each other (500ms)
            if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                return;
            }

            // reset the shake count after 3 seconds of no shakes
            if (mShakeTimestamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                mShakeCount = 0;
            }

            mShakeTimestamp = now;
            mShakeCount++;

            setChanged();
            float[] position = new float[2];
            position[0] = 20;
            position[1] = 100;
            if(mShakeCount >=1)
                notifyObservers(position);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}

