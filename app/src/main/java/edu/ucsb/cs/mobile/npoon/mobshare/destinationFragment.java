package edu.ucsb.cs.mobile.npoon.mobshare;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nickkpoon on 12/19/17.
 */

public class destinationFragment extends Fragment {

    private ImageButton LosAngeles;
    private ImageButton SanFrancisco;
    private ImageButton LasVegas;
    private ImageButton  SanDiego;
    private TextView title;

    private ArrayList<listingItem> listItems = new ArrayList<listingItem>();
    private ArrayList<listingItem> Filtered = new ArrayList<listingItem>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View view = inflater.inflate(R.layout.destination_select, container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int stageHeight = display.getHeight();



        LosAngeles = view.findViewById(R.id.LosAngeles);
        SanFrancisco = view.findViewById(R.id.SanFrancisco);
        LasVegas = view.findViewById(R.id.LasVegas);
        SanDiego = view.findViewById(R.id.SanDiego);
        title = view.findViewById(R.id.dest_title);

        Typeface logoFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TitilliumWeb-ExtraLight.ttf");
        title.setTypeface(logoFont);

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

        LasVegas.setOnClickListener(new View.OnClickListener() {
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
       // Log.d("GOT TO FRAGMENT!", listItems.get(0).getDate());


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
        Filtered.clear();
        switch (Destination)
        {
            case 0:
                //Toast.makeText(getActivity(), "LA", Toast.LENGTH_SHORT).show();
                for(int i = 0; i<listItems.size(); i++){

                    if(listItems.get(i).getDestination() != null)
                    {
                        if(listItems.get(i).getDestination().equals("Los Angeles") )
                        {
                            //Log.d("Destination: ", listItems.get(i).getDestination());
                             Filtered.add(listItems.get(i));

                        }

                    }

                }
                bundle.putInt("Dest", 0);
                break;

            case 1:
                //Toast.makeText(getActivity(), "SF", Toast.LENGTH_SHORT).show();
                for(int i = 0; i<listItems.size(); i++){

                    if(listItems.get(i).getDestination() != null)
                    {
                        if(listItems.get(i).getDestination().equals("San Francisco" )){
                            Filtered.add(listItems.get(i));
                        }

                    }

                }
                bundle.putInt("Dest", 1);
                break;

            case 2:
                //Toast.makeText(getActivity(), "LV", Toast.LENGTH_SHORT).show();
                for(int i = 0; i<listItems.size(); i++){

                    if(listItems.get(i).getDestination() != null)
                    {
                        if(listItems.get(i).getDestination().equals("Las Vegas")){
                            Filtered.add(listItems.get(i));
                        }

                    }





                }
                bundle.putInt("Dest", 2);
                break;

            case 3:
                //Toast.makeText(getActivity(), "SD", Toast.LENGTH_SHORT).show();
                for(int i = 0; i<listItems.size(); i++){

                    if(listItems.get(i).getDestination() != null)
                    {
                        if(listItems.get(i).getDestination().equals("San Diego") ){
                            Filtered.add(listItems.get(i));
                        }

                    }





                }
                bundle.putInt("Dest", 3);
                break;

        }

        Collections.sort(Filtered);
        bundle.putParcelableArrayList("CardList", Filtered);
        //Log.d("PASSED TO FRAGMENT2!", listItems.get(0).getDate());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //newFragment = new listingPage();
        newFragment = new listingPage();
        newFragment.setArguments(bundle);
        transaction.replace(R.id.fragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

