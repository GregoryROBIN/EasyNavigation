package fr.unice.polytech.easynavigation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by chapon on 06/10/16.
 */

public class UpDetector implements SensorEventListener {

    private OnUpListener mlistener;
    float [] mRotationMatrix = new float[16];
    float [] mOrientation = new float[9];
    float [] history = new float[3];
    float mHeading;
    float mPitch;

    public void setOnUpListener(OnUpListener listener) {
        this.mlistener = listener;
    }

    interface OnUpListener {
        void onUp();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float xChange = history[0] - event.values[0];
            float yChange = history[1] - event.values[1]; //Verschil tussen nieuwe en oude positie ophalen.
            float zChange = history[2] - event.values[2];
            Log.d("x", ""+xChange);
            Log.d("y", ""+yChange);
            Log.d("z", ""+zChange);
            history[0] = event.values[0];
            history[1] = event.values[1]; //Nieuwe waarden bewaren voor volgende event trigger

            if (xChange > 2){

            }
            else if (zChange < -2){
                mlistener.onUp();
            }


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To do


    }
}
