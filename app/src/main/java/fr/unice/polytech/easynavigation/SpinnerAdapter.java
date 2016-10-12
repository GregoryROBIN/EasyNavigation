package fr.unice.polytech.easynavigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


/**
 * Adaptater pour le spinner
 * pour descendre dans le menu
 */

public class SpinnerAdapter extends ArrayAdapter<MenuItem> {
    public SpinnerAdapter(Context context, int resource, List<MenuItem> categories) {
        super(context, resource, categories);
    }
    int pos=-1;

    public void setPosition(int position){
        this.pos = position;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = null;
        row = super.getDropDownView(position, convertView, parent);
        if(position == pos){
            row.setBackgroundResource(R.drawable.bg_selected);
        }

        else
            row.setBackgroundColor(Color.WHITE);
        return row;
    }
}
