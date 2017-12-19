package edu.ucsb.cs.cs184.npoon.mobshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Julio on 12/3/2017.
 */


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

    private void addRideShare(){
        String username = "Bob";
        String price = editTextPrice.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String tripType = spinnerTripType.getSelectedItem().toString();
        String[] spinnerCheck_array = getResources().getStringArray(R.array.items);

        if((!TextUtils.isEmpty(price)) && (!TextUtils.isEmpty(date))){

            if(tripType.equals(spinnerCheck_array[0])){
                Toast.makeText(this,"Please Select a type of Trip!", Toast.LENGTH_LONG).show();
            }
                    else {
                String id = databaserideShare.push().getKey();
                rideShare rS = new rideShare(username, tripType, price, date);
                databaserideShare.child(id).setValue(rS);
            }
        }
        else{
            Toast.makeText(
                    this,
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

    public rideShare() {

    }

    public rideShare(String rSUsername, String rStripType, String rSprice, String rSdate) {

        this.rSUsername = rSUsername;
        this.rStripType = rStripType;
        this.rSprice = rSprice;
        this.rSdate = rSdate;
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
        }
    }
