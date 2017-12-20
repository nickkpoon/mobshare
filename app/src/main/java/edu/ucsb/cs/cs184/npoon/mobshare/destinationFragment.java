package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by nickkpoon on 12/19/17.
 */

public class destinationFragment extends Fragment {

    private TextView LosAngeles;
    private TextView SanFrancisco;
    private TextView Sacramento;
    private TextView SanDiego;

    private ArrayList<listingItem> listItems = new ArrayList<listingItem>();

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

        Bundle bundle = this.getArguments();
        listItems = bundle.getParcelableArrayList("CardList");
        Log.d("GOT TO FRAGMENT!", listItems.get(0).getDate());


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
        Fragment newFragment;
        Bundle bundle = new Bundle();
        switch (Destination)
        {
            case 0:
                Toast.makeText(getActivity(), "LA", Toast.LENGTH_SHORT).show();
                bundle.putInt("Dest", 0);
                break;

            case 1:
                Toast.makeText(getActivity(), "SF", Toast.LENGTH_SHORT).show();
                bundle.putInt("Dest", 1);
                break;

            case 2:
                Toast.makeText(getActivity(), "SAC", Toast.LENGTH_SHORT).show();
                bundle.putInt("Dest", 2);
                break;

            case 3:
                Toast.makeText(getActivity(), "SD", Toast.LENGTH_SHORT).show();
                bundle.putInt("Dest", 3);
                break;

        }


        bundle.putParcelableArrayList("CardList", listItems);
        Log.d("PASSED TO FRAGMENT2!", listItems.get(0).getDate());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //newFragment = new listingPage();
        newFragment = new listingPage();
        newFragment.setArguments(bundle);
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

