package fr.unice.polytech.easynavigation.version2.longTouch;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import fr.unice.polytech.easynavigation.version2.CustomSpinner;

/**
 * Created by chapon on 13/10/16.
 */

public class CapteurLongTouch  implements View.OnTouchListener, Observer {

    public boolean longClik =false;
    GestureDetectorImplementation gestureDetectorImplementation;
    GestureDetector gestureDetector;
    Context context;
    CustomSpinner menu;
    boolean touchOK=false;

    public CapteurLongTouch(Context c, CustomSpinner menu) {
        this.context = c;
        this.menu = menu;

        this.gestureDetectorImplementation = new GestureDetectorImplementation(this);
        gestureDetector = new GestureDetector(context, gestureDetectorImplementation);


    }
    public void start(){
        touchOK =true;
    }
    public void stop(){
        touchOK = false;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(touchOK) {
            gestureDetector.onTouchEvent(event);
            return true;
        }else
            return true;
    }

    @Override
    public void update(Observable observable, Object data) {
        if ((data != null)) {
            float x,y ;
            float[] donneesRecues = (float[]) data;
            x = donneesRecues[0];
            y = donneesRecues[1];
            float menuWidth = menu.getMenuWidth();
            float menuHeigth = menu.getMenuHeigth();
            float newX = x - menuWidth/2;
            float newY = y - menuHeigth/2;
            menu.setX(newX);
            menu.setY(newY);
            menu.setVisibility(View.VISIBLE);
            menu.performClick();
            longClik = true;
        }
    }
}
