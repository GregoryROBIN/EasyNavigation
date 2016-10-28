package fr.unice.polytech.easynavigation.applicationTraditionnelle;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.easynavigation.R;

/**
 * Created by chapon on 08/10/16.
 */

public class SportFragment extends Fragment {
    public SportFragment() {
    }


    public static SportFragment newInstance() {
        SportFragment fragment = new SportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_accueil, container, false);
        TextView title = (TextView) rootView.findViewById(R.id.title);
        TextView content = (TextView) rootView.findViewById(R.id.content);
        ImageView image1 = (ImageView) rootView.findViewById(R.id.image1);
        ImageView image2 = (ImageView) rootView.findViewById(R.id.image2);
        image1.setImageResource(R.drawable.sports);
        image2.setImageResource(R.drawable.football);
        title.setText("Actualités Sportives");
        content.setText("PSG 0 - 0 Marseille");
        return rootView;
    }
}
