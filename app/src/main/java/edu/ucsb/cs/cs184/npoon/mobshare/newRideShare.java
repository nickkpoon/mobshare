package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Julio on 12/3/2017.
 */


/*

//Old code as an activity, DO NOT DELETE!!!
public class newRideShare extends AppCompatActivity {

    double sourceLatitude = 41.008238;
    double sourceLongitude = 28.978359;
    double destinationLatitude = 37.983810;
    double destinationLongitude = 23.727539;


    EditText editTextDate;
    EditText editTextPrice;
    Spinner spinnerTripType;
    Button buttonSubmit;
    Button buttonLaunchMaps;
    TextView textViewDest;

    DatabaseReference databaserideShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rideshare);

        databaserideShare = FirebaseDatabase.getInstance().getReference("rideShare");

        editTextDate =      findViewById(R.id.editTextDate);
        editTextPrice =     findViewById(R.id.editTextPrice);

        spinnerTripType = findViewById(R.id.tripType);

        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonLaunchMaps = findViewById(R.id.buttonLaunchMaps);
        textViewDest = findViewById(R.id.textViewDest);

        buttonLaunchMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchMaps();

                Log.d("ADebugTag", "Value: " + Double.toString(destinationLatitude));

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addRideShare();
            }

        });


    }
*/

public class newRideShare extends Fragment {

    double sourceLatitude = 41.008238;
    double sourceLongitude = 28.978359;
    double destinationLatitude = 37.983810;
    double destinationLongitude = 23.727539;


    EditText editTextDate;
    EditText editTextPrice;
    Spinner spinnerTripType;
    Button buttonSubmit;
    Button buttonLaunchMaps;
    TextView textViewDest;
    private FirebaseAuth mAuth;

    DatabaseReference databaserideShare;
    FirebaseDatabase User;
    DatabaseReference UserRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_rideshare, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        databaserideShare = FirebaseDatabase.getInstance().getReference("rideShare");
        User = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        View v = getView();
        editTextDate =      v.findViewById(R.id.editTextDate);
        editTextPrice =     v.findViewById(R.id.editTextPrice);

        spinnerTripType =   v.findViewById(R.id.tripType);

        buttonSubmit =      v.findViewById(R.id.buttonSubmit);

        buttonLaunchMaps =  v.findViewById(R.id.buttonLaunchMaps);
        textViewDest =      v.findViewById(R.id.textViewDest);

        buttonLaunchMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchMaps();

                Log.d("ADebugTag", "Value: " + Double.toString(destinationLatitude));

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addRideShare();
            }

        });

    }



    private void addRideShare(){
        final String price = editTextPrice.getText().toString().trim();
        final String date = editTextDate.getText().toString().trim();
        final String tripType = spinnerTripType.getSelectedItem().toString();
        String[] spinnerCheck_array = getResources().getStringArray(R.array.items);
        UserRef = User.getReference("Users").child(mAuth.getUid().toString());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String NameValue = dataSnapshot.child("Name").getValue(String.class);
                String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                String PhoneValue = dataSnapshot.child("Phone").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        if((!TextUtils.isEmpty(price)) && (!TextUtils.isEmpty(date))){

            if(tripType.equals(spinnerCheck_array[0])){
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(),"Please Select a type of Trip!", Toast.LENGTH_LONG).show();
            }
            else {
                final String id = databaserideShare.push().getKey();

                UserRef = User.getReference("Users").child(mAuth.getUid().toString());
                UserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String NameValue = dataSnapshot.child("Name").getValue(String.class);
                        String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                        String PhoneValue = dataSnapshot.child("Phone").getValue(String.class);
                        rideShare rS = new rideShare(UNameValue, tripType, price, date, PhoneValue, NameValue);
                        databaserideShare.child(id).child("Username").setValue(UNameValue);
                        databaserideShare.child(id).child("Name").setValue(NameValue);
                        databaserideShare.child(id).child("Trip Type").setValue(tripType);
                        databaserideShare.child(id).child("Price").setValue(price);
                        databaserideShare.child(id).child("Date").setValue(date);
                        databaserideShare.child(id).child("Phone Number").setValue(PhoneValue);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                //rideShare rS = new rideShare(username, tripType, price, date, );
                //databaserideShare.child(id).setValue(rS);
            }
        }
        else{
            Toast.makeText(newRideShare.this.getActivity().getApplicationContext(),
                    "One or more values is empty, please make sure they are all filled out.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void launchMaps(){

        String uri = "http://maps.google.com/maps?saddr=" + sourceLatitude + "," + sourceLongitude + "&daddr=" + destinationLatitude + "," + destinationLongitude;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    public class rideShare {

        private String rSUsername;
        private String rStripType;
        private String rSprice;
        private String rSdate;
        private String rSRealName;
        private String rSPhone;

        public rideShare() {

        }

        public rideShare(String rSUsername, String rStripType, String rSprice, String rSdate, String rSPhone, String rSRealName) {

            this.rSUsername = rSUsername;
            this.rStripType = rStripType;
            this.rSprice = rSprice;
            this.rSdate = rSdate;
            this.rSPhone = rSPhone;
            this.rSRealName = rSRealName;
        }

        public String getrSUsername() { return rSUsername; }

        public String getrStripType() {
            return rStripType;
        }

        public String getrSprice() {
            return rSprice;
        }

        public String getrSdate() {
            return rSdate;
        }

        public String getrSRealName() {
            return rSRealName;
        }
        public String getrSPhone() {
            return rSPhone;
        }
    }
}
