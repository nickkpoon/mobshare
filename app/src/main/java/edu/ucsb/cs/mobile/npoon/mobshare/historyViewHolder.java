package edu.ucsb.cs.mobile.npoon.mobshare;

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

    public void setRS(historyItem rS, Boolean RT) {
        this.displayRideshare = rS;


//        Username.setText("Driver: " + rS.getName());
        DestinationHist.setText(rS.getDestination());
        TypeHist.setText(rS.getTrip_Type());

        Depart_DateHist.setText("Departure: " + rS.getDepart_Date());
        Depart_TimeHist.setText(rS.getDepart_Time());


//        Phone.setText("Driver Phone Number: " + rS.getPhone_Number());
        if(RT) {
            Return_DateHist.setText("Return: " + rS.getReturn_Date());
            Return_TimeHist.setText(rS.getReturn_Time());
        }

        PriceHist.setText("$" + rS.getPrice());

    }
}
