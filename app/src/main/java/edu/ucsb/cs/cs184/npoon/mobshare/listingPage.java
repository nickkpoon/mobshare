package edu.ucsb.cs.cs184.npoon.mobshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julio on 12/9/2017.
 */

public class listingPage extends AppCompatActivity implements ViewHolder.newRideShareListener {

    private RecyclerView recyclerView;

    private List<listingItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listingspage);

        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listingsAdapter adapter = new listingsAdapter(getLayoutInflater(), this);
        recyclerView.setAdapter(adapter);



        listItems = new ArrayList<>();

        for(int i=0; i<=10;i++){
            listingItem newItem = new listingItem(
                    "USERNAME" + (i+1), "One WAY", "$" + (i+100),(i)+"/"+(i)+"/"+(i)
            );
        listItems.add(newItem);
    }
}

//    @Override
//    public void onMapClicked(newRideShare item) {
//
//    }

    @Override
    public void onTitleClicked(newRideShare item) {



    }
}
