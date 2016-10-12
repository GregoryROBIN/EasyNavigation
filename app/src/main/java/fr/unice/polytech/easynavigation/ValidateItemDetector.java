package fr.unice.polytech.easynavigation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Created by chapon on 06/10/16.
 */

public class ValidateItemDetector implements SensorEventListener {

    private OnValidateItemListener mlistener;



    public void setOnValidateItemListener(OnValidateItemListener listener){
        this.mlistener =listener;
    }

    public interface OnValidateItemListener {
        void onValidateItem();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
       //To do
        //mlistener.onValidateItem();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To do

    }
}
