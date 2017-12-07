package edu.ucsb.cs.cs184.npoon.mobshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Julio on 12/3/2017.
 */


public class newRideShare extends AppCompatActivity {

    EditText editTextDate;
    EditText editTextUsername;
    EditText editTextPrice;
    ToggleButton toggleTripType;
    Button buttonSubmit;

    DatabaseReference databaserideShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rideshare);

        databaserideShare = FirebaseDatabase.getInstance().getReference("rideShare");

        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);

        toggleTripType = (ToggleButton) findViewById(R.id.toggleTripType);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addRideShare();
            }

        });


    }

    private void addRideShare(){
        String username = editTextUsername.getText().toString().trim();
        String price = editTextPrice.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String toggle = toggleTripType.getText().toString().trim();

        if((!TextUtils.isEmpty(username) && (!TextUtils.isEmpty(price)) && (!TextUtils.isEmpty(date)))){
            String id = databaserideShare.push().getKey();
            rideShare rS = new rideShare(username, price, date, toggle);
            databaserideShare.child(id).setValue(rS);
        }else{
            Toast.makeText(
                    this,
                    "One or more values is empty, please make sure they are all filled out.",
                    Toast.LENGTH_LONG).show();
        }
    }

public class rideShare {

    private String rSusername;
    private String rSprice;
    private String rSdate;
    private String rStoggle;

    public rideShare(){

    }

    public rideShare(String rSusername, String rSprice, String rSdate, String rStoggle){

        this.rSusername = rSusername;
        this.rSprice = rSprice;
        this.rSdate = rSdate;
        this.rStoggle = rStoggle;
    }

    public String getrSusername() { return rSusername; }

    public String getrSprice() {
        return rSprice;
    }

    public String getrSdate() {
        return rSdate;
    }

    public String getrStoggle() { return rStoggle;
        }
    }
}
