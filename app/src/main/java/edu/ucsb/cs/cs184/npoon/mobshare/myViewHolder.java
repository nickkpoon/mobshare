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
    TextView Username;
    TextView Price;
    TextView Date;
    TextView Type;
    TextView Destination;
    TextView Phone;

    listingItem displayRideshare;


    public myViewHolder(View itemView) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        Username = (TextView) itemView.findViewById(R.id.Name);
        Price = (TextView) itemView.findViewById(R.id.Price);
        Date = (TextView) itemView.findViewById(R.id.Date);
        Type = (TextView) itemView.findViewById(R.id.Type);
        Destination = (TextView) itemView.findViewById(R.id.Destination);
        Phone = (TextView) itemView.findViewById(R.id.Phone);
    }

    public void setRS(listingItem rS) {
        this.displayRideshare = rS;
        Price.setText(rS.getPrice());
        Username.setText(rS.getName());
        Date.setText(rS.getDate());
        Type.setText(rS.getTripType());
        Destination.setText(rS.getDestination());
        Phone.setText(rS.getPhone());
    }
}
