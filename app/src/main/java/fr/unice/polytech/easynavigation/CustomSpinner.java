package fr.unice.polytech.easynavigation;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;

/**
 * Created by chapon on 07/10/16.
 */

public class CustomSpinner extends Spinner implements ValidateItemDetector.OnValidateItemListener{


    private UpDetector.OnUpListener mListener;
    private int courantActivated;
    private boolean isDropDownMenuShown=false;

    public CustomSpinner(Context context) {
        super(context);
    }
    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUpListener(UpDetector.OnUpListener listener){
        mListener = listener;
    }

    public void initCourantActivated(int position){
        setCourantActivated(position);
    }
    public int getCourantActivated(){
        return courantActivated;
    }
    public void setCourantActivated(int position){
        this.courantActivated = position;
    }
    public void increaseCourantActivated(){
        int item = this.getCourantActivated();
        int nbItem = this.getCount();
        if(item+1 < nbItem) {
            this.setCourantActivated(item+1);

        }else {
            this.setCourantActivated(0);
        }
    }




    @Override
    public boolean performClick() {
        this.isDropDownMenuShown = true;
        return super.performClick();
    }



    public boolean isDropDownMenuShown(){
        return this.isDropDownMenuShown;
    }

    public void setDropDownMenuShown(boolean dropDownMenuShown) {
        isDropDownMenuShown = dropDownMenuShown;
    }


    @Override
    public void onValidateItem() {
        if(isDropDownMenuShown) {
            //Log.d("Skae", "ok");
            //this.setSelection(this.getCourantActivated());
        }

    }
}
