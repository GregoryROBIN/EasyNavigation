package fr.unice.polytech.easynavigation.version2.upDownDetection;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import fr.unice.polytech.easynavigation.version2.CustomSpinner;

/**
 * Created by chapon on 13/10/16.
 */

public class InterpreteurUpDown implements Observer {

    CustomSpinner menu;

    public InterpreteurUpDown(CustomSpinner menu) {
        this.menu = menu;
    }


    @Override
    public void update(Observable observable, Object data) {
        if ((data != null)) {
            String donnees = (String) data;
            if(donnees.equals("colorGreen")){
                //Log.d("Utilisateur", donnees);
                menu.setSelection(menu.getCourantActivated());
                menu.performClosedEvent();
            }else if(donnees.equals("descendre"))
            {
                //Log.d("utilisateur","descendre");
                menu.increaseCourantActivated(1);

            }
        }

    }
}
