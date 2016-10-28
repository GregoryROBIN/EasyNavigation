package fr.unice.polytech.easynavigation.setting;

import fr.unice.polytech.easynavigation.version2.CustomSpinner;

/**
 * Created by chapon on 27/10/16.
 */

public class DataBase {
    public static DataBase database = new DataBase();
    private boolean activeShake = false;
    private boolean activeUpDown = false;
    private boolean activeLongTouch = false;
    private SettingListener settingListener;

    public boolean isActiveLongTouch() {
        return activeLongTouch;
    }

    public void setActiveLongTouch(boolean activeLongTouch) {
        this.activeLongTouch = activeLongTouch;
        settingListener.onChangeLongClick();
    }

    public boolean isActiveUpDown() {
        return activeUpDown;
    }

    public void setActiveUpDown(boolean activeUpDown) {
        this.activeUpDown = activeUpDown;
        settingListener.onChangeUpDown();
    }

    public boolean isActiveShake() {
        return activeShake;
    }

    public void setActiveShake(boolean activeShake) {
        this.activeShake = activeShake;
        settingListener.onChangeShaker();
    }

    public void setSettingListener(SettingListener listener){
        this.settingListener = listener;
    }
    public interface SettingListener{
        void onChangeUpDown();
        void onChangeShaker();
        void onChangeLongClick();
    }
}
