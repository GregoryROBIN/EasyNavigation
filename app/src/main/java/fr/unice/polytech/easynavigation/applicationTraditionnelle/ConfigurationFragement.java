package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import fr.unice.polytech.easynavigation.setting.DataBase;
import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 13/10/16.
 */

public class ConfigurationFragement extends Fragment {


    public ConfigurationFragement() {
    }


    public static ConfigurationFragement newInstance() {
        ConfigurationFragement fragment = new ConfigurationFragement();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.configuration, container, false);
        CheckBox longTouch = (CheckBox) rootView.findViewById(R.id.longtouch);
        CheckBox upDown = (CheckBox) rootView.findViewById(R.id.accelero);
        CheckBox shake = (CheckBox) rootView.findViewById(R.id.shaker);
        longTouch.setChecked(DataBase.database.isActiveLongTouch());
        upDown.setChecked(DataBase.database.isActiveUpDown());
        shake.setChecked(DataBase.database.isActiveShake());

        longTouch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataBase.database.setActiveLongTouch(isChecked);

            }
        });
        upDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataBase.database.setActiveUpDown(isChecked);
            }
        });
        shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataBase.database.setActiveShake(isChecked);
            }
        });
        return rootView;
    }


}