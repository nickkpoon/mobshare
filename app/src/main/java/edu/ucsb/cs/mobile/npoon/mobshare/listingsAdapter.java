package edu.ucsb.cs.mobile.npoon.mobshare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Julio on 12/9/2017.
 */

public class listingsAdapter extends RecyclerView.Adapter<myViewHolder> {

    public interface OnItemClickListener {
        void onItemClicked(View v);
    }
    private OnItemClickListener listener;

    private Context context;

    private List<listingItem> listItems = new ArrayList<listingItem>();

    public listingsAdapter(OnItemClickListener listener, List <listingItem> rShare, Context contextList) {
        super();
        this.listener = listener;

        this.listItems = rShare;

        this.context = context;

    }

    @Override
    public void onBindViewHolder(myViewHolder myViewHolder, int i) {

        Boolean RT;

        if(listItems.get(i).getReturn_Date().equals("N/A"))
        {
            Log.d(TAG, listItems.get(i).getReturn_Date());

            Log.d(TAG, "RT = FALSE");

            RT = false;
        }

        else
        {
            RT = true;
        }


        myViewHolder.setRS(listItems.get(i), RT);

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item, viewGroup, false);
        myViewHolder vh = new myViewHolder(v, listener);
        return vh;
    }

    @Override
    public int getItemCount() {
//        return 10;
        Log.d(TAG, "getItemCount: " + listItems.size());
            return listItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
