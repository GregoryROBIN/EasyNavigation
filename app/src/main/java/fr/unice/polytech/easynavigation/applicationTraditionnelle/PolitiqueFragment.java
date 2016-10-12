package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 08/10/16.
 */

public class PolitiqueFragment extends android.support.v4.app.Fragment {

    public PolitiqueFragment() {
    }


    public static PolitiqueFragment newInstance() {
        PolitiqueFragment fragment = new PolitiqueFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_accueil, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("Politique Page");
        return rootView;
    }
}
