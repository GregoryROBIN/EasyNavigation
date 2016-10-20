package fr.unice.polytech.easynavigation.version2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import fr.unice.polytech.easynavigation.R;
import fr.unice.polytech.easynavigation.version2.upDownDetection.CapteurUpDown;
import fr.unice.polytech.easynavigation.version2.upDownDetection.InterpreteurUpDown;
import fr.unice.polytech.easynavigation.version2.longTouch.CapteurLongTouch;
import fr.unice.polytech.easynavigation.version2.shacker.CapteurShaker;
import fr.unice.polytech.easynavigation.version2.shacker.InterpreteurShacker;

/**
 * General activity
 */

public abstract class GeneralActivity extends AppCompatActivity implements CustomSpinner.OpenMenuListener {
    //View general initialisé dans l'activité fille
    protected View frame;
    protected List<MenuItem> categories;
    //Shacker
    private InterpreteurShacker controleurShacker ;
    private CapteurShaker capteurShaker;
    //Le menu déroulant
    private CustomSpinner menu;
    private SpinnerAdapter dataAdapter;
    //Long touch
    private CapteurLongTouch capteurLongTouch;

    //Pour descendre avec l'accelerometre dans le menu
    private CapteurUpDown capteurUpDown;
    private InterpreteurUpDown interpreteurUpDown;


    private boolean onClickItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Initialisation du menu
        menu = (CustomSpinner) findViewById(R.id.dropMenu);
        dataAdapter = new SpinnerAdapter(this, R.layout.custom_spinner, categories);
        dataAdapter.setDropDownViewResource(R.layout.custom_spinner);
        menu.setAdapter(dataAdapter);
        menu.setOpenMenuListener(this);
        menu.setVisibility(View.INVISIBLE);
        menu.setCourantActivated(menu.getSelectedItemPosition());
        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onClickItem = true;
                MenuItem item = dataAdapter.getItem(position);
                menu.setCourantActivated(position);
                menu.performClosedEvent();
                loadPage(item);
                onClickItem = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //initialisation du shacker
        capteurShaker = new CapteurShaker(this);
        controleurShacker = new InterpreteurShacker(menu);
        capteurShaker.addObserver(controleurShacker);
        capteurShaker.start();


        //initialisation du longClick
        capteurLongTouch = new CapteurLongTouch(this,menu );
        frame.setOnTouchListener(capteurLongTouch);


        //initialisation du CapteurUpDown
        capteurUpDown = new CapteurUpDown(this);
        interpreteurUpDown = new InterpreteurUpDown(menu);
        capteurUpDown.addObserver(interpreteurUpDown);


    }

    @Override
    protected void onResume(){
        super.onPause();
        if(menu.isDropDownMenuShown()){
            capteurUpDown.start();
        }else
        {
            capteurShaker.start();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        capteurUpDown.stop();
        capteurShaker.stop();
    }
    @Override
    protected void onPause() {
        super.onPause();
        capteurUpDown.stop();
        capteurShaker.stop();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //super.onWindowFocusChanged(hasFocus);
        // mSpin is our custom Spinner
        if (menu.isDropDownMenuShown() && hasFocus && !this.onClickItem) {
            int position = menu.getCourantActivated();
            MenuItem item = dataAdapter.getItem(position);
            menu.setCourantActivated(position);
            menu.performClosedEvent();
            loadPage(item);
        }


    }

    @Override
    public void onOpenMenu(){
        Log.d("Menu","ouvert");
        capteurShaker.stop();
        capteurUpDown.start();

    }

    @Override
    public void onCloseMenu() {
        Log.d("Menu", "fermé");
        capteurUpDown.stop();
        capteurShaker.start();
        menu.setVisibility(View.INVISIBLE);
    }

    public abstract void loadPage(MenuItem item);
}
