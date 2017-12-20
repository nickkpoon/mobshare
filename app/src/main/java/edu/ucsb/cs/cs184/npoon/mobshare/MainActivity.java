package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseDatabase User;
    private DatabaseReference MyRef;
    private Button signOut;
    private Button createListing;
    private DrawerLayout drawer;




    private ArrayList<listingItem> listItems = new ArrayList<listingItem>();
    private ArrayList<historyItem> histItems = new ArrayList<historyItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //---------------------------------------------------

        initializeData(0);
//---------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        signOut = findViewById(R.id.nav_signOut);
        createListing = findViewById(R.id.nav_create);

        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        User = FirebaseDatabase.getInstance();



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, WelcomeScreen.class);
                startActivity(intent);

            }
        });

        createListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                newFragment = new newRideShare();
                transaction.replace(R.id.fragment, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                drawer.closeDrawers();

            }
        });




    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final TextView RealName = (TextView) findViewById(R.id.RealName);
        final TextView UserName = (TextView) findViewById(R.id.UserName);
        MyRef = User.getReference("Users").child(mAuth.getUid().toString());
        MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String NameValue = dataSnapshot.child("Name").getValue(String.class);
                String UNameValue = dataSnapshot.child("UserName").getValue(String.class);
                RealName.setText(NameValue);
                UserName.setText(UNameValue);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile_button)
        {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();

            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            newFragment = new profileFragment();

            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        else if (id == R.id.listings_button)
        {
            //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();

            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();


            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("CardList", listItems);
//            Log.d("PASSED TO FRAGMENT!", listItems.get(0).getDate());

            newFragment = new destinationFragment();
            newFragment.setArguments(bundle);
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
        else if (id == R.id.history_button)
        {
//            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();


            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("CardList", histItems);
//            Log.d("PASSED TO FRAGMENT!", listItems.get(0).getDate());

            newFragment = new historyListingPage();
            newFragment.setArguments(bundle);
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        /*else if (id == R.id.newRide_button)
        {
            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();

            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            newFragment = new newRideShare();
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if (id == R.id.signOut_button)
        {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, WelcomeScreen.class);
            startActivity(intent);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onCreate(savedInstanceState);
    }

//TODO:: FIREBASE STUFF
    private void initializeData(int Destination) {
        listItems = new ArrayList<>();
        histItems = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference MyRef = db.getReference("rideShare");
        MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
 //                   Log.d("FB item PULL", "PULLED!");

                    listingItem newItem = snapshot.getValue(listingItem.class);
                    historyItem newItemHist = snapshot.getValue(historyItem.class);

                    String NameValue = newItem.getName();
//                    Log.d("NAME", NameValue);

                    String DestinationHist = newItemHist.getDestinationHist();
                    String TypeHist = newItemHist.getTrip_TypeHist();
                    String DepartDateHist = newItemHist.getDepart_DateHist();
                    String DepartTimeHist = newItemHist.getDepart_TimeHist();
                    String ReturnDateHist = newItemHist.getReturn_DateHist();
                    String ReturnTimeHist = newItemHist.getReturn_TimeHist();
                    String PriceHist = newItemHist.getPriceHist();

                    String Price = newItem.getPrice();
                    String Type = newItem.getTrip_Type();
                    String Date = newItem.getDepart_Date();
                    //Log.d("DATE:  ", Date);
                    //String Destination = "LA";
                    String Phone = newItem.getPhone_Number();

                    listItems.add(newItem);
                    histItems.add(newItemHist);
                    //Log.d("LISTITEMS ADDED!  ", newItem.getDate());


                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG , "Failed to read value.",error.toException());
            }
        });

    }
}
