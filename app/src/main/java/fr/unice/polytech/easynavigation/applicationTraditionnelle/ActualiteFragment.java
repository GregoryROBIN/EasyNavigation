package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 13/10/16.
 */

public class ActualiteFragment extends Fragment {


    public ActualiteFragment() {
    }


    public static ActualiteFragment newInstance() {
        ActualiteFragment fragment = new ActualiteFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_accueil, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("Actualité");
        return rootView;
    }

}
