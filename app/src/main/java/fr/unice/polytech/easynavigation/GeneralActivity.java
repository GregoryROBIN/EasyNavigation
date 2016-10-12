package fr.unice.polytech.easynavigation;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;


import java.util.Arrays;
import java.util.List;

/**
 * General activity
 */

public abstract class GeneralActivity extends AppCompatActivity {
    //Contient la taille du bouton menu
    private float menuWidth;
    private float menuHeigth;

    //Notre menu
    private CustomSpinner menu;
    private SpinnerAdapter dataAdapter;

    //Shake
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    //Up
    private UpDetector mUpDetector;

    //Touch
    private LongClickDetector longClick;

    //ValidateItemDetector
    private ValidateItemDetector validateItemDetector;

    //Liste de tout le menu
    protected List<MenuItem> categories;

    //La vue courante
    protected View frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initialisationAdapter();
        initialisationShaker();
        initialisationUpDetector();
        initialisationLongTouch();
        initialisationItemValidateDetector();
    }

    private void initialisationItemValidateDetector() {
        validateItemDetector = new ValidateItemDetector();
        validateItemDetector.setOnValidateItemListener(menu);
    }


    private void initialisationUpDetector() {
        mUpDetector = new UpDetector();
        mUpDetector.setOnUpListener(new UpDetector.OnUpListener() {
            @Override
            public void onUp() {
                //Si le menu est ouvert alors on ecoute
                if(menu.isDropDownMenuShown()) {
                    Log.d("Up", "ok");
                    menu.increaseCourantActivated();
                    dataAdapter.setPosition(menu.getCourantActivated());
                    dataAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void initialisationShaker() {
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                    float newX = 0;
                    float newY = 0;
                    menu.setX(newX);
                    menu.setY(newY);
                    menu.setVisibility(View.VISIBLE);
                    menu.performClick();
            }
        });
    }

    private void initialisationLongTouch() {
        longClick = new LongClickDetector();
        frame.setOnTouchListener(longClick);
        longClick.setOnLongClick(new LongClickDetector.OnLongClick() {
            @Override
            public void onLongTouch(float x, float y) {
                float newX = x - menuWidth/2;
                float newY = y - menuHeigth/2;
                menu.setX(newX);
                menu.setY(newY);
                menu.setVisibility(View.VISIBLE);
                menu.performClick();
            }
        });
    }

    private void initialisationAdapter() {
        //Initialisation du menu
        menu = (CustomSpinner) findViewById(R.id.dropMenu);
        dataAdapter = new SpinnerAdapter(this, R.layout.custom_spinner, categories);
        dataAdapter.setDropDownViewResource(R.layout.custom_spinner);
        menu.setAdapter(dataAdapter);
        menu.initCourantActivated(2);
        dataAdapter.setPosition(menu.getCourantActivated());
        dataAdapter.notifyDataSetChanged();

        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MenuItem itemSelected = dataAdapter.getItem(position);
                dataAdapter.setPosition(position);
                menu.setCourantActivated(position);
                dataAdapter.notifyDataSetChanged();
                loadPage(itemSelected);
                menu.setVisibility(View.INVISIBLE);
                menu.setDropDownMenuShown(false);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Nothing to do
            }
        });
    }

    protected void updateMenuActiveted(MenuItem item) {
        int position = dataAdapter.getPosition(item);
        if(menu.getSelectedItemPosition() != position){
            menu.setSelection(position);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        menuWidth = menu.getWidth();
        menuHeigth = menu.getHeight();
        if (menu.isDropDownMenuShown() && menu.getSelectedItemPosition() != menu.getCourantActivated()) {
            menu.setDropDownMenuShown(false);
            menu.performClick();
        }else {
            menu.setVisibility(View.INVISIBLE);
            menu.setDropDownMenuShown(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(mUpDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        mSensorManager.unregisterListener(mShakeDetector);
        mSensorManager.unregisterListener(mUpDetector);
        super.onPause();
    }



    public abstract void loadPage(MenuItem item);
}
