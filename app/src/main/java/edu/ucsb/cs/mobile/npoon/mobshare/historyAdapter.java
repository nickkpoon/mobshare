package edu.ucsb.cs.mobile.npoon.mobshare;

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

public class historyAdapter extends RecyclerView.Adapter<historyViewHolder> {

    private List<historyItem> histItems = new ArrayList<historyItem>();

    public historyAdapter(List<historyItem> rShare) {
        this.histItems = rShare;
    }

    @Override
    public void onBindViewHolder(historyViewHolder historyViewHolder, int i) {

        Boolean RT;

        if(histItems.get(i).getReturn_Date().equals("N/A"))
        {
            Log.d(TAG, histItems.get(i).getReturn_Date());

            Log.d(TAG, "RT = FALSE");

            RT = false;
        }

        else
        {
            RT = true;
        }
        historyViewHolder.setRS(histItems.get(i), RT);
    }

    @Override
    public historyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_card_item, viewGroup, false);
        historyViewHolder vh = new historyViewHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {
//        return 10;
        Log.d(TAG, "getItemCount: " + histItems.size());
        return histItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
