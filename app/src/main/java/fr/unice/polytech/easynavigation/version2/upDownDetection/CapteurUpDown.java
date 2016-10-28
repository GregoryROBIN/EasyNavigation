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


    float[] mMagnetValues      = new float[3];
    float[] mAccelValues       = new float[3];
    float[] mOrientationValues = new float[3];
    float[] mRotationMatrix    = new float[9];
    Context context;
    Display display;
    private float mCurrentPosition;
    private boolean isCurrentPositionSet = false;
    private int count = 0;
    private Sensor mAccelerometer;
    private SensorManager mSensorManager;
    private Sensor mMagnetic;
    private boolean running = false;
    private boolean reach=false;


    public CapteurUpDown(Context c) {
        this.context = c;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();

    }

    public void stop() {
        mSensorManager.unregisterListener(this);
        running = false;
    }

    public void start() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        running = true;

    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                System.arraycopy(event.values, 0, mAccelValues, 0, 3);
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                System.arraycopy(event.values, 0, mMagnetValues, 0, 3);
                break;
        }
        SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelValues, mMagnetValues);
        SensorManager.getOrientation(mRotationMatrix, mOrientationValues);
        //Pour le up il faut detecter un changement sur mOrientationValues[1] de 0.25

        if(mCurrentPosition + 0.5 < mOrientationValues[1]) {
            reach = false;
            //monter
        }
        else if (mCurrentPosition - 0.5 > mOrientationValues[1]) {
            setChanged();
            notifyObservers("descendre");
            reach =false;

        }
        if(!reach){
            mCurrentPosition = mOrientationValues[1];
            reach = true;
        }


    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}