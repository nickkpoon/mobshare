package edu.ucsb.cs.cs184.npoon.mobshare;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Julio on 12/17/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView textUsername;
    TextView textPrice;
    TextView textDate;
    TextView textType;
//    ImageView mapIcon;
    newRideShare.rideShare displayRideshare;
    newRideShareListener listener;

    public interface newRideShareListener{
//        void onMapClicked(newRideShare item);
        void onTitleClicked(newRideShare item);
    }

    public ViewHolder(View itemView, final newRideShareListener listener) {
        super(itemView);
        this.listener = listener;

        textUsername = (TextView) itemView.findViewById(R.id.textViewUsername);
        textPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
        textDate = (TextView) itemView.findViewById(R.id.textViewDate);
        textType = (TextView) itemView.findViewById(R.id.textViewTripType);

        //        textLocation = (TextView) itemView.findViewById(R.id.text_location);
//        textDate = (TextView) itemView.findViewById(R.id.text_date);
//        mapIcon = (ImageView) itemView.findViewById(R.id.map_icon);

//        textUsername.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onTitleClicked(displayRideshare);
//            }
//        });
//        mapIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onMapClicked(displayRideshare);
//            }
//        });
    }

    public void setRS(newRideShare.rideShare rS) {
        this.displayRideshare = rS;
        textPrice.setText(rS.getrSprice());
//        textLocation.setText(item.getLocationName());
//        textDate.setText(getFormattedDate(item));
    }

//    private String getFormattedDate(ExcellentAdventure item) {
//        String date = item.getYear() + " " + item.getEra();
//        return date;
//    }


}
