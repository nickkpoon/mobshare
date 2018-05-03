package edu.ucsb.cs.mobile.npoon.mobshare;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * Created by nickkpoon on 12/20/17.
 */

public class profileFragment extends Fragment {


    private FirebaseDatabase User;
    private DatabaseReference UserRef;
    private FirebaseAuth mAuth;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mAuth = FirebaseAuth.getInstance();
        User = FirebaseDatabase.getInstance();
        final TextView name = (TextView) view.findViewById(R.id.profile_name);

        Typeface logoFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TitilliumWeb-ExtraLight.ttf");
        name.setTypeface(logoFont);


        final TextView phone_num = view.findViewById(R.id.profile_number);
        final TextView rides_given = view.findViewById(R.id.profile_given);
        //final TextView rides_taken = view.findViewById(R.id.profile_taken);
        UserRef = User.getReference("Users").child(mAuth.getUid().toString());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String NameValue = dataSnapshot.child("Name").getValue(String.class);
                Integer RidesTaken = dataSnapshot.child("Rides Taken").getValue(Integer.class);
                Integer RidesGiven = dataSnapshot.child("Rides Given").getValue(Integer.class);
                String PhoneValue = dataSnapshot.child("Phone Number").getValue(String.class);
                name.setText(NameValue);
                phone_num.setText("Number: " + PhoneValue);
                rides_given.setText("Rides Given: " + String.valueOf(RidesGiven));
                //rides_taken.setText("Rides Taken: " + String.valueOf(RidesTaken));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle save)
    {
        super.onCreate(save);
        /*mAuth = FirebaseAuth.getInstance();
        User = FirebaseDatabase.getInstance();
        final TextView name = (TextView) getActivity().findViewById(R.id.profile_name);
        final TextView phone_num = (TextView) getActivity().findViewById(R.id.profile_number);
        final TextView rides_given = getActivity().findViewById(R.id.profile_given);
        final TextView rides_taken = getActivity().findViewById(R.id.profile_taken);
        UserRef = User.getReference("Users").child(mAuth.getUid().toString());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String NameValue = dataSnapshot.child("Name").getValue(String.class);
                //Integer RidesTaken = dataSnapshot.child("Rides Taken").getValue(Integer.class);
                // Integer RidesGiven = dataSnapshot.child("Rides Given").getValue(Integer.class);
                String PhoneValue = dataSnapshot.child("Phone").getValue(String.class);
                name.setText(NameValue);
                phone_num.setText(PhoneValue);
                //rides_given.setText(RidesGiven);
                //rides_taken.setText(RidesTaken);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        setRetainInstance(true);
    }


}
