package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.design.widget.NavigationView;

import java.util.ArrayList;

import fr.unice.polytech.easynavigation.version2.GeneralActivity;
import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 07/10/16.
 */

public class AccueilActivity extends GeneralActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Menu de l'application que l'on va transformer
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);
        this.frame =  (FrameLayout)findViewById(R.id.content_frame);
        fillOurMenu();
        loadAccueilPage();
    }

    private void fillOurMenu() {
        categories = new ArrayList<>();
        for(int i=0; i<menu.size(); i++){
            categories.add(menu.getItem(i));
        }

    }


    private void loadAccueilPage() {
        Fragment fragment = null;
        try {
            fragment = (Fragment) AccueilFragment.newInstance();
            if(fragment == null)
                System.out.println("Le fragment est nul");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment !=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void loadPage(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.sport:
                loadSportPage();
                break;
            case R.id.politique:
                loadPolitiquePage();
                break;
            case R.id.accueil:
                loadAccueilPage();
                break;
            case R.id.histoire:
                loadPageHistoire();
                break;
            case R.id.actualite:
                loadPageActualite();
                break;
        }
    }

    private void loadPageActualite() {
        Fragment fragment = null;
        try {
            fragment = (Fragment) ActualiteFragment.newInstance();
            if(fragment == null)
                System.out.println("Le fragment est nul");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment !=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }
    }

    private void loadPageHistoire() {
        Fragment fragment = null;
        try {
            fragment = (Fragment) HistoireFragment.newInstance();
            if(fragment == null)
                System.out.println("Le fragment est nul");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment !=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }
    }

    private void loadPolitiquePage() {
        Fragment fragment = null;
        try {
            fragment = (Fragment) PolitiqueFragment.newInstance();
            if(fragment == null)
                System.out.println("Le fragment est nul");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment !=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }
    }

    private void loadSportPage() {
        Fragment fragment = null;
        try {
            fragment = (Fragment) SportFragment.newInstance();
            if(fragment == null)
                System.out.println("Le fragment est nul");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragment !=null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //updateMenuActiveted(item);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}