package edu.ucsb.cs.cs184.npoon.mobshare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Julio on 12/17/2017.
 */

public class myViewHolder extends RecyclerView.ViewHolder{

    CardView cv;
    TextView textUsername;
    TextView textPrice;
    TextView textDate;
    TextView textType;

    listingItem displayRideshare;


    public myViewHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        textUsername = (TextView) itemView.findViewById(R.id.textViewUsername);
        textPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        textDate = (TextView) itemView.findViewById(R.id.textViewDate);
        textType = (TextView) itemView.findViewById(R.id.textViewTripType);
    }

    public void setRS(listingItem rS) {
        this.displayRideshare = rS;
        textPrice.setText(rS.getPrice());
        textUsername.setText(rS.getUsername());
        textDate.setText(rS.getDate());
        textType.setText(rS.getTripType());
    }
}
