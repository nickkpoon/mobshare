package edu.ucsb.cs.mobile.npoon.mobshare;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by Julio on 12/3/2017.
 */

public class newRideShare extends Fragment {


    private TextView mDisplayDepartD;
    private TextView mDisplayReturnD;

    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    private TextView mDisplayDepartT;
    private TextView mDisplayReturnT;

    private TimePickerDialog.OnTimeSetListener mTimeSetListener1;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener2;
    private long dateResult;


    EditText editTextPrice;
    Spinner spinnerDestination;
    Spinner spinnerTripType;
    Button buttonSubmit;
    TextView title;

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

        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        databaserideShare = FirebaseDatabase.getInstance().getReference("rideShare");
        User = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        View v = getView();

        title=v.findViewById(R.id.rideshare_title);
        Typeface logoFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/TitilliumWeb-ExtraLight.ttf");
        title.setTypeface(logoFont);


        editTextPrice = v.findViewById(R.id.editTextPrice);

        spinnerDestination = v.findViewById(R.id.tripDestination);

        spinnerTripType = v.findViewById(R.id.tripType);

        buttonSubmit = v.findViewById(R.id.buttonSubmit);

        mDisplayDepartD = (TextView) v.findViewById(R.id.textViewDepartD);
        mDisplayReturnD = (TextView) v.findViewById(R.id.textViewReturnD);

        mDisplayDepartT = (TextView) v.findViewById(R.id.textViewDepartT);
        mDisplayReturnT = (TextView) v.findViewById(R.id.textViewReturnT);


        mDisplayDepartD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                long minDate = cal.getTime().getTime();


                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Dialog,
                        mDateSetListener1,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getDatePicker().setMinDate(minDate);
                dialog.show();
            }
        });

        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = "Departure Date:  " + month + "/" + day + "/" + year;


                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                Date dateT = null;
                try {
                    dateT = (Date)formatter.parse(month + "/" + day + "/" + year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long mills = dateT.getTime();
                dateResult = mills;

                mDisplayDepartD.setText(date);
            }
        };

        mDisplayReturnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                long minDate = cal.getTime().getTime();




                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Dialog,
                        mDateSetListener2,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                if(TextUtils.isEmpty(mDisplayDepartD.getText())) {
                    Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Departure Date First!", Toast.LENGTH_LONG).show();
                }
                else{
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getDatePicker().setMinDate(dateResult);
                    dialog.show();
                }
            }
        });

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = "Return Date:  " + month + "/" + day + "/" + year;
                mDisplayReturnD.setText(date);
            }
        };


        mDisplayDepartT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        getActivity(), mTimeSetListener1, hour, minute,
                        DateFormat.is24HourFormat(getActivity()));

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener1 =new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet (TimePicker timePicker,int hour, int minute){

                String time = getTime(hour, minute);

                mDisplayDepartT.setText("Departure Time:  " + time);
            }
        };


        mDisplayReturnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        getActivity(), mTimeSetListener2, hour, minute,
                        DateFormat.is24HourFormat(getActivity()));


                if(TextUtils.isEmpty(mDisplayDepartT.getText())) {
                    Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Departure Time First!", Toast.LENGTH_LONG).show();
                }
                else{

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
            }
        });

        mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                String time = getTime(hour, minute);

                mDisplayReturnT.setText("Return Time:  " + time);

            }
        };

        final String[] spinnerCheck_array = getResources().getStringArray(R.array.items);



        spinnerTripType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

//                position = 1;
                if(spinnerCheck_array[position].equals("One Way")){

                    mDisplayReturnT.setVisibility(View.GONE);
                    mDisplayReturnD.setVisibility(View.GONE);
                }
                else{

                    mDisplayReturnT.setVisibility(View.VISIBLE);
                    mDisplayReturnD.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRideShare();
            }

        });

    }

    private String getTime(int hr, int min) {
        Time tme = new Time(hr, min, 0);
        Format formatter;
        formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(tme);
    };



    private void addRideShare() {
        String[] spinnerCheck_array = getResources().getStringArray(R.array.items);
        String[] spinnerCheck_array2 = getResources().getStringArray(R.array.items2);
        final String destination = spinnerDestination.getSelectedItem().toString();
        final String price = editTextPrice.getText().toString().trim();
        final String tripType = spinnerTripType.getSelectedItem().toString();

        final String dateD = mDisplayDepartD.getText().toString().trim().replace("Departure Date:  ","");
        final String dateR = mDisplayReturnD.getText().toString().trim().replace("Return Date:  ","");

        final String timeD = mDisplayDepartT.getText().toString().trim().replace("Departure Time:  ","");
        final String timeR = mDisplayReturnT.getText().toString().trim().replace("Return Time:  ","");

        UserRef = User.getReference("Users").child(mAuth.getUid().toString());
        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String NameValue = dataSnapshot.child("Name").getValue(String.class);
                String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                String PhoneValue = dataSnapshot.child("Phone Number").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        if ((!TextUtils.isEmpty(price)))
        {

            if (destination.equals(spinnerCheck_array2[0]))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Destination!", Toast.LENGTH_LONG).show();
            }else if (tripType.equals(spinnerCheck_array[0]))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a type of Trip!", Toast.LENGTH_LONG).show();
            }else if (dateD.equals(""))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Departure Date!", Toast.LENGTH_LONG).show();
            }else if (timeD.equals(""))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Departure Time!", Toast.LENGTH_LONG).show();
            }else if ((dateR.equals("")) && (!tripType.equals(spinnerCheck_array[1])))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Return Date!", Toast.LENGTH_LONG).show();
            }else if ((timeR.equals("")) && (!tripType.equals(spinnerCheck_array[1])))
            {
                Toast.makeText(newRideShare.this.getActivity().getApplicationContext(), "Please Select a Return Time!", Toast.LENGTH_LONG).show();
            }
            else {

                if (tripType.equals(spinnerCheck_array[1])) {

                    final String id = databaserideShare.push().getKey();

                    UserRef = User.getReference("Users").child(mAuth.getUid().toString());
                    UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String NameValue = dataSnapshot.child("Name").getValue(String.class);
                            String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                            String PhoneValue = dataSnapshot.child("Phone Number").getValue(String.class);
//                        rideShare rS = new rideShare(UNameValue, destination, tripType, price, date, PhoneValue, NameValue);
                            Integer gave = dataSnapshot.child("Rides Given").getValue(Integer.class);
                            UserRef.child("Rides Given").setValue(gave + 1);
                            databaserideShare.child(id).child("Rides Given").setValue(gave + 1);
                            databaserideShare.child(id).child("Username").setValue(UNameValue);
                            databaserideShare.child(id).child("Name").setValue(NameValue);
                            databaserideShare.child(id).child("Destination").setValue(destination);
                            databaserideShare.child(id).child("Trip_Type").setValue(tripType);
                            databaserideShare.child(id).child("Price").setValue(price);
                            databaserideShare.child(id).child("Depart_Date").setValue(dateD);
                            databaserideShare.child(id).child("Return_Date").setValue("N/A");
                            databaserideShare.child(id).child("Depart_Time").setValue(timeD);
                            databaserideShare.child(id).child("Return_Time").setValue("N/A");
                            databaserideShare.child(id).child("Phone_Number").setValue(PhoneValue);
                            UserRef.child("rideShares").child(id).child("Return_Time").setValue("N/A");
                            UserRef.child("rideShares").child(id).child("Depart_Time").setValue(timeD);
                            UserRef.child("rideShares").child(id).child("Return_Date").setValue("N/A");
                            UserRef.child("rideShares").child(id).child("Depart_Date").setValue(dateD);
                            UserRef.child("rideShares").child(id).child("Price").setValue(price);
                            UserRef.child("rideShares").child(id).child("Destination").setValue(destination);

                            Toast.makeText(newRideShare.this.getActivity().getApplicationContext(),
                                    "Ride Share Posted!",
                                    Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                    // Failed to read value
                    //Log.w(TAG, "Failed to read value.", error.toException());}


                } else {
                    final String id = databaserideShare.push().getKey();

                    UserRef = User.getReference("Users").child(mAuth.getUid().toString());
                    UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String NameValue = dataSnapshot.child("Name").getValue(String.class);
                            String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                            String PhoneValue = dataSnapshot.child("Phone Number").getValue(String.class);
                            Integer gave = dataSnapshot.child("Rides Given").getValue(Integer.class);
                            UserRef.child("Rides Given").setValue(gave + 1);
                            databaserideShare.child(id).child("Rides Given").setValue(gave + 1);
                            databaserideShare.child(id).child("Username").setValue(UNameValue);
                            databaserideShare.child(id).child("Name").setValue(NameValue);
                            databaserideShare.child(id).child("Destination").setValue(destination);
                            databaserideShare.child(id).child("Trip_Type").setValue(tripType);
                            databaserideShare.child(id).child("Price").setValue(price);
                            databaserideShare.child(id).child("Depart_Date").setValue(dateD);
                            databaserideShare.child(id).child("Return_Date").setValue(dateR);
                            databaserideShare.child(id).child("Depart_Time").setValue(timeD);
                            databaserideShare.child(id).child("Return_Time").setValue(timeR);
                            databaserideShare.child(id).child("Phone_Number").setValue(PhoneValue);
                            UserRef.child("rideShares").child(id).child("Return_Time").setValue(timeR);
                            UserRef.child("rideShares").child(id).child("Depart_Time").setValue(timeD);
                            UserRef.child("rideShares").child(id).child("Return_Date").setValue(dateR);
                            UserRef.child("rideShares").child(id).child("Depart_Date").setValue(dateD);
                            UserRef.child("rideShares").child(id).child("Price").setValue(price);
                            UserRef.child("rideShares").child(id).child("Destination").setValue(destination);

                            Toast.makeText(newRideShare.this.getActivity().getApplicationContext(),
                                    "Ride Share Posted!",
                                    Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }


        }
        else{
            Toast.makeText(newRideShare.this.getActivity().getApplicationContext(),
                    "Please Enter a Price!",
                    Toast.LENGTH_LONG).show();
        }


    }

    public class rideShare {

        private String rSUsername;
        private String rSDestination;
        private String rStripType;
        private String rSprice;
        private String rSdate;
        private String rSRealName;
        private String rSPhone;

        public rideShare() {

        }

        public rideShare(String rSDestination, String rSUsername, String rStripType, String rSprice, String rSdate, String rSPhone, String rSRealName) {

            this.rSDestination = rSDestination;
            this.rSUsername = rSUsername;
            this.rStripType = rStripType;
            this.rSprice = rSprice;
            this.rSdate = rSdate;
            this.rSPhone = rSPhone;
            this.rSRealName = rSRealName;
        }

        public String getrSUsername() { return rSUsername; }

        public String getrSDestination() { return rSDestination; }

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