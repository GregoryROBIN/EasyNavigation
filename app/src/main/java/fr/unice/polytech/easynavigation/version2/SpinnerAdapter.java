package fr.unice.polytech.easynavigation.version2;

import android.content.Context;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import fr.unice.polytech.easynavigation.R;


/**
 * Adaptater pour le spinner
 * pour descendre dans le menu
 */

public class SpinnerAdapter extends ArrayAdapter<MenuItem> {
    int pos=-1;
    public SpinnerAdapter(Context context, int resource, List<MenuItem> categories) {
        super(context, resource, categories);
    }

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
