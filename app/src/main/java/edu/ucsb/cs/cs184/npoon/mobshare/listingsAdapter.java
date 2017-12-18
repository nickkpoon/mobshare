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

public class listingsAdapter extends RecyclerView.Adapter {

    private List<newRideShare.rideShare> listItems;
    private LayoutInflater inflater;
    private ViewHolder.newRideShareListener rideShareListener;

    public listingsAdapter(LayoutInflater inflater, ViewHolder.newRideShareListener rideShareListener) {
        this.inflater = inflater;
        this.rideShareListener = rideShareListener;
        listItems = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.card_item, parent, false);

        return new ViewHolder(v, rideShareListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh = (ViewHolder) holder;

        vh.setRS(listItems.get(position));

//        listingItem lItem = listItems.get(position);
//
//        vh.textViewHeader.setText(lItem.getHeader());
//        vh.textViewDescription.setText(lItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        public TextView textViewHeader;
//        public TextView textViewDescription;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            textViewHeader = (TextView) itemView.findViewById(R.id.textViewHeading);
//            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
//        }
//    }
}
