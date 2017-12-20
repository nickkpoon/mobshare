package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class listingPage extends Fragment {

    private List<listingItem> listItems;
    private int dest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_listingspage, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            dest = bundle.getInt("Dest");
        }


        initializeData(dest);
        View v = getView();
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(listingPage.this.getActivity().getApplicationContext()));

        listingsAdapter adapter = new listingsAdapter(listItems);
        recyclerView.setAdapter(adapter);


    }

/*

public class listingPage extends AppCompatActivity {

    private List<listingItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listingspage);
        initializeData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listingsAdapter adapter = new listingsAdapter(listItems);
        recyclerView.setAdapter(adapter);

    }
*/

    private void initializeData(int Destination) {

        switch (Destination)
        {
            case 0:
                Toast.makeText(getActivity(), "LA BUNDLE", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(getActivity(), "SF BUNDLE", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(getActivity(), "SAC BUNDLE", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                Toast.makeText(getActivity(), "SD BUNDLE", Toast.LENGTH_SHORT).show();
                break;

        }

        listItems = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            listingItem newItem = new listingItem(
                    "USERNAME" + (i + 1), "One WAY", "$" + (i + 100), (i) + "/" + (i) + "/" + (i)
            );
            listItems.add(newItem);
        }
    }
}

