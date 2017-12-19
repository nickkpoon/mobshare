package edu.ucsb.cs.cs184.npoon.mobshare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julio on 12/9/2017.
 */

public class listingsAdapter extends RecyclerView.Adapter<myViewHolder> {

    private List<listingItem> listItems = new ArrayList<listingItem>();

    public listingsAdapter(List<listingItem> rShare) {
    this.listItems = rShare;
    }

    @Override
    public void onBindViewHolder(myViewHolder myViewHolder, int i) {
        myViewHolder.setRS(listItems.get(i));
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        myViewHolder vh = new myViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
//        return 10;
            return listItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
