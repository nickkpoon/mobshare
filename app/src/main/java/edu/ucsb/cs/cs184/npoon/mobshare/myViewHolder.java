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
    TextView Return_Date;

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
        Return_Date = (TextView) itemView.findViewById(R.id.Return_Date);
    }

    public void setRS(listingItem rS) {
        this.displayRideshare = rS;
        Price.setText("Ride Price: " + rS.getPrice());
        Username.setText("Driver: " + rS.getName());
        Date.setText("Departure Date: " + rS.getDepart_Date() + "       " + "Departure Time: " + rS.getDepart_Time());
        Type.setText("Trip Type: " + rS.getTrip_Type());
        Destination.setText("Destination: " + rS.getDestination());
        Phone.setText("Driver Phone Number: " + rS.getPhone_Number());
        Return_Date.setText("Return Date: " + rS.getReturn_Date() + "       " + "Return Time: " + rS.getReturn_Time());

    }
}
