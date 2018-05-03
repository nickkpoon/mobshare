package edu.ucsb.cs.mobile.npoon.mobshare;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
        final String Phone = mArgs.getString("Phone");
        final String Username = mArgs.getString("Username");
        String RealName = mArgs.getString("RealName");
        Integer Rides = mArgs.getInt("Rides");
        TextView Name = view.findViewById(R.id.profile_name_popup);
        TextView Email = view.findViewById(R.id.profile_email_popup);
        TextView PhoneNumber = view.findViewById(R.id.profile_number_popup);
        TextView GivenRides = view.findViewById(R.id.profile_given);
        Button callButton = view.findViewById(R.id.dialog_call);
        Button mailButton = view.findViewById(R.id.dialog_text);
        Name.setText(RealName);
        Email.setText("Email: " + Username);
        PhoneNumber.setText("Phone: " + Phone);
        GivenRides.setText("Rides Posted: " + String.valueOf(Rides));
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.setContentView(view);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + Phone));

                if (ContextCompat.checkSelfPermission( getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( getActivity(), new String[]{android.Manifest.permission.CALL_PHONE}, 1234);
                }
                else
                {
                    getActivity().startActivity(callIntent);
                }
            }
        });


        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", Username, null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        return builder;

    }
}