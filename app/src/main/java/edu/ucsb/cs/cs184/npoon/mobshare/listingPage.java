package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.content.ContentValues.TAG;


public class listingPage extends Fragment implements listingsAdapter.OnItemClickListener{
    //private static final String TAG = MyActivity.class.getName();
    private List<listingItem> listItems;
    private int dest;

    private listingsAdapter.OnItemClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_listingspage, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dest = bundle.getInt("Dest");
            listItems = bundle.getParcelableArrayList("CardList");
            //Log.d("GOT TO FRAGMENT2!", listItems.get(0).getDate());

            Log.d(TAG, "CardList: ");
        }


        View v = getView();
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(listingPage.this.getActivity().getApplicationContext()));

        listingsAdapter adapter = new listingsAdapter(this, listItems, getActivity());
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onItemClicked(View v) {
        dialogFragmentCard df= new dialogFragmentCard();
        df.show(getFragmentManager(), "Dialog");
    }

}

