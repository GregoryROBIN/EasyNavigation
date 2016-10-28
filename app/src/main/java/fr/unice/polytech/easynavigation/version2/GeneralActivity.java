package fr.unice.polytech.easynavigation.version2;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import fr.unice.polytech.easynavigation.setting.DataBase;
import fr.unice.polytech.easynavigation.R;
import fr.unice.polytech.easynavigation.version2.upDownDetection.CapteurUpDown;
import fr.unice.polytech.easynavigation.version2.upDownDetection.InterpreteurUpDown;
import fr.unice.polytech.easynavigation.version2.longTouch.CapteurLongTouch;
import fr.unice.polytech.easynavigation.version2.shacker.CapteurShaker;
import fr.unice.polytech.easynavigation.version2.shacker.InterpreteurShacker;

/**
 * General activity
 */

public abstract class GeneralActivity extends AppCompatActivity implements CustomSpinner.OpenMenuListener, DataBase.SettingListener {
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
        menu.setOnItemSelectedListener(new CustomSpinner.ItemSelectionListener() {
                                           @Override
                                           public void onItemSelected(int position) {
                                               onClickItem = true;
                                               MenuItem item = dataAdapter.getItem(position);
                                               menu.setCourantActivated(position);
                                               menu.performClosedEvent();
                                               Log.d("FRAG", "Selection");

                                               loadPage(item);
                                               onClickItem = false;
                                           }
                                       });

                //initialisation du shacker
        capteurShaker = new CapteurShaker(this);
        controleurShacker = new InterpreteurShacker(menu);
        capteurShaker.addObserver(controleurShacker);
        capteurShaker.start();


        //initialisation du longClick
        capteurLongTouch = new CapteurLongTouch(this,menu );
        capteurLongTouch.start();
        frame.setOnTouchListener(capteurLongTouch);


        //initialisation du CapteurUpDown
        capteurUpDown = new CapteurUpDown(this);
        interpreteurUpDown = new InterpreteurUpDown(menu);
        capteurUpDown.addObserver(interpreteurUpDown);
        DataBase.database.setSettingListener(this);
        DataBase.database.setActiveUpDown(false);
        DataBase.database.setActiveShake(false);
        DataBase.database.setActiveLongTouch(true);


    }

    @Override
    protected void onResume(){
        super.onPause();
        if(menu.isDropDownMenuShown() && DataBase.database.isActiveUpDown()){
            capteurUpDown.start();
        }else if(DataBase.database.isActiveShake())
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
        if(DataBase.database.isActiveUpDown())
            capteurUpDown.start();

    }

    @Override
    public void onCloseMenu() {
        Log.d("Menu", "fermé");
        capteurUpDown.stop();
        if(DataBase.database.isActiveShake())
            capteurShaker.start();
        menu.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onChangeUpDown() {
        Log.d("UpDown","Changed");
        if(DataBase.database.isActiveUpDown())
            capteurUpDown.start();
        else
            capteurUpDown.stop();
    }

    @Override
    public void onChangeShaker() {
        if(DataBase.database.isActiveShake())
            capteurShaker.start();
        else
            capteurShaker.stop();
    }

    @Override
    public void onChangeLongClick() {
        if(DataBase.database.isActiveLongTouch())
            capteurLongTouch.start();
        else
            capteurLongTouch.stop();
    }

    public abstract void loadPage(MenuItem item);
}
