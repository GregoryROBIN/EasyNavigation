package fr.unice.polytech.easynavigation.version2.shacker;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Observable;
import java.util.Observer;

import fr.unice.polytech.easynavigation.version2.CustomSpinner;


public class InterpreteurShacker implements Observer {


    public boolean onShake =false;
    CustomSpinner menu;


    public InterpreteurShacker(CustomSpinner menu) {
        this.menu = menu;
    }


    @Override
    public void update(Observable observable, Object data) {
        if ((data != null)) {
            float x,y ;
            float[] donneesRecues = (float[]) data;
            x = donneesRecues[0];
            y = donneesRecues[1];
            menu.setX(x);
            menu.setY(y);
            menu.setVisibility(View.VISIBLE);
            menu.performClick();
            onShake =true;
        }

    }
}
