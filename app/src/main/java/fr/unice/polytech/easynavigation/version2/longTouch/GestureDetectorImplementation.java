package fr.unice.polytech.easynavigation.version2.longTouch;

import android.util.Log;
import android.view.MotionEvent;

import java.util.Observable;

/**
 * Created by chapon on 13/10/16.
 */

public class GestureDetectorImplementation extends Observable implements android.view.GestureDetector.OnGestureListener {


    public GestureDetectorImplementation(CapteurLongTouch capteurLongTouch) {
        this.addObserver(capteurLongTouch);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        float[] position = new float[2];
        position[0] = e.getX();
        position[1] = e.getY();
        setChanged();
        notifyObservers(position);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
