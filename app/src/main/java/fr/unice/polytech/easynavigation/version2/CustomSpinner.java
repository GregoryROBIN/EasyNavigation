package fr.unice.polytech.easynavigation.version2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by chapon on 07/10/16.
 */

public class CustomSpinner extends Spinner {


    float menuWidth;
    float menuHeigth;
    private int courantActivated;
    private boolean isDropDownMenuShown=false;
    private SpinnerAdapter spinnerAdapter;
    /* Interface pour detection ouverture menu */
    private OpenMenuListener openMenuListener;
    public CustomSpinner(Context context) {
        super(context);
    }
    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public CustomSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getCourantActivated(){
        return courantActivated;
    }

    public void setCourantActivated(int position){
        this.courantActivated = position;
        if(spinnerAdapter == null){
            spinnerAdapter = (SpinnerAdapter)this.getAdapter();
        }
        this.spinnerAdapter.setPosition(position);
    }

    public void increaseCourantActivated(int valeur){
        int item = this.getCourantActivated();
        int nbItem = this.getCount();
        if(item+valeur < nbItem ) {
            this.setCourantActivated(item+valeur);
        }else if (item+valeur <0){
            this.setCourantActivated(nbItem+valeur);
        }else {
            this.setCourantActivated(0);
        }
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int
            heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec );
        menuWidth= getMeasuredWidth();
        menuHeigth = getMeasuredHeight();
    }

    public float getMenuWidth() {
        return menuWidth;
    }

    public float getMenuHeigth() {
        return menuHeigth;
    }

    @Override
    public boolean performClick() {
        openMenuListener.onOpenMenu();
        this.isDropDownMenuShown = true;
        return super.performClick();
    }

    public void performClosedEvent() {
        this.setDropDownMenuShown(false);
        openMenuListener.onCloseMenu();
    }

    public boolean isDropDownMenuShown(){
        return this.isDropDownMenuShown;
    }

    public void setDropDownMenuShown(boolean dropDownMenuShown) {
        isDropDownMenuShown = dropDownMenuShown;
    }

    public void setOpenMenuListener(OpenMenuListener listener){
        this.openMenuListener = listener;
    }
    public interface OpenMenuListener{
        void onOpenMenu();
        void onCloseMenu();
    }


}
