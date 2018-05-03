package edu.ucsb.cs.mobile.npoon.mobshare;

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
    TextView Return_Date;


    listingItem displayRideshare;


    public myViewHolder(View itemView, final listingsAdapter.OnItemClickListener listener) {
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.cv);
        Username = (TextView) itemView.findViewById(R.id.Name);
        Price = (TextView) itemView.findViewById(R.id.Price);
        Date = (TextView) itemView.findViewById(R.id.Date);
        Type = (TextView) itemView.findViewById(R.id.Type);
        Destination = (TextView) itemView.findViewById(R.id.Destination);
        Phone = (TextView) itemView.findViewById(R.id.Phone);
        Return_Date = (TextView) itemView.findViewById(R.id.Return_Date);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(v);
            }
        });

    }

    public void setRS(listingItem rS, Boolean RT) {
        this.displayRideshare = rS;
        Price.setText("$" + rS.getPrice());
        Username.setText("Driver: " + rS.getName());
        Date.setText("Depart: " + rS.getDepart_Date() + "       Time: " + rS.getDepart_Time());
        Type.setText(rS.getTrip_Type());
        Destination.setText(rS.getDestination());
        Phone.setText("Number: " + rS.getPhone_Number());

        if(RT)
        Return_Date.setText("Return: " + rS.getReturn_Date() + "       Time: " + rS.getReturn_Time());



    }
}
