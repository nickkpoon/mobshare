package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Julio on 12/20/2017.
 */

public class dialogFragmentCard extends DialogFragment {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_dialog_card, new LinearLayout(getActivity()), false);


        Dialog builder = new Dialog(getActivity());
        Bundle mArgs = getArguments();
        String Phone = mArgs.getString("Phone");
        String Username = mArgs.getString("Username");
        String RealName = mArgs.getString("RealName");
        Integer Rides = mArgs.getInt("Rides");
        TextView Name = view.findViewById(R.id.profile_name_popup);
        TextView Email = view.findViewById(R.id.profile_email_popup);
        TextView PhoneNumber = view.findViewById(R.id.profile_number_popup);
        TextView GivenRides = view.findViewById(R.id.profile_given);
        Name.setText("Driver Name: " + RealName);
        Email.setText("Driver Email: " + Username);
        PhoneNumber.setText("Driver Phone Number: " + Phone);
        GivenRides.setText("Number of Rides Posted: " + String.valueOf(Rides));
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        builder.setContentView(view);
        return builder;

    }
}