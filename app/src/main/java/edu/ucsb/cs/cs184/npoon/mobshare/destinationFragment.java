package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by nickkpoon on 12/19/17.
 */

public class destinationFragment extends Fragment {

    private Button LosAngeles;
    private Button SanFrancisco;
    private Button Sacramento;
    private Button SanDiego;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.destination_select, container, false);
        LosAngeles = view.findViewById(R.id.LosAngeles);
        SanFrancisco = view.findViewById(R.id.SanFrancisco);
        Sacramento = view.findViewById(R.id.Sacramento);
        SanDiego = view.findViewById(R.id.SanDiego);

        LosAngeles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(0);
            }
        });

        SanFrancisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(1);

            }
        });

        Sacramento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(2);

            }
        });

        SanDiego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(3);

            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle save)
    {
        super.onCreate(save);
        setRetainInstance(true);
    }


    public void goToFragment(int Destination)
    {
        switch (Destination)
        {
            case 0: Toast.makeText(getActivity(), "LA", Toast.LENGTH_SHORT).show();
            break;

            case 1: Toast.makeText(getActivity(), "SF", Toast.LENGTH_SHORT).show();
            break;

            case 2: Toast.makeText(getActivity(), "SAC", Toast.LENGTH_SHORT).show();
            break;

            case 3: Toast.makeText(getActivity(), "SD", Toast.LENGTH_SHORT).show();
            break;

        }
        Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //newFragment = new listingPage();
        newFragment = new listingPage();
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

