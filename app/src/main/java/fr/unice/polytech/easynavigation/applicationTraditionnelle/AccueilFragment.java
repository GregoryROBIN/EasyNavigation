package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 10/04/16.
 */
public class AccueilFragment extends Fragment {


    public AccueilFragment() {
    }


    public static AccueilFragment newInstance() {
        AccueilFragment fragment = new AccueilFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_accueil, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.content);
        TextView title = (TextView) rootView.findViewById(R.id.title);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
        image2.setImageResource(R.drawable.menu);
        image1.setImageResource(R.drawable.naviguer);
        title.setText("Accueil");
        textView.setText("Une toute nouvelle façon de naviguer ...");
        return rootView;
    }

}
