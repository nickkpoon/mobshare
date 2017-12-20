package edu.ucsb.cs.cs184.npoon.mobshare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Julio on 12/17/2017.
 */

public class historyViewHolder extends RecyclerView.ViewHolder{

    CardView cv2;

    TextView DestinationHist;
    TextView TypeHist;

    TextView Depart_DateHist;
    TextView Depart_TimeHist;

    TextView Return_DateHist;
    TextView Return_TimeHist;

    TextView PriceHist;


    historyItem displayRideshare;


    public historyViewHolder(View itemView) {
        super(itemView);

        cv2 = (CardView) itemView.findViewById(R.id.cv2);

        DestinationHist = (TextView) itemView.findViewById(R.id.DestinationHist);
        TypeHist = (TextView) itemView.findViewById(R.id.TypeHist);

        Depart_DateHist = (TextView) itemView.findViewById(R.id.Depart_DateHist);
        Depart_TimeHist = (TextView) itemView.findViewById(R.id.Depart_TimeHist);

        Return_DateHist = (TextView) itemView.findViewById(R.id.Return_DateHist);
        Return_TimeHist = (TextView) itemView.findViewById(R.id.Return_TimeHist);

        PriceHist = (TextView) itemView.findViewById(R.id.PriceHist);

    }

    public void setRS(historyItem rS) {
        this.displayRideshare = rS;


//        Username.setText("Driver: " + rS.getName());
        DestinationHist.setText("Destination: " + rS.getDestination());
        TypeHist.setText("Trip Type: " + rS.getTrip_Type());

        Depart_DateHist.setText("Departure Date: " + rS.getDepart_Date());
        Depart_TimeHist.setText("Departure Time: " + rS.getDepart_Time());


//        Phone.setText("Driver Phone Number: " + rS.getPhone_Number());
        Return_DateHist.setText("Return Date: " + rS.getReturn_Date());
        Return_TimeHist.setText("Return Time: " + rS.getReturn_Time());

        PriceHist.setText("Ride Price: " + rS.getPrice());

    }
}
