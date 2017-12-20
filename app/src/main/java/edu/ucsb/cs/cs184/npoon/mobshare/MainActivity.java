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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseDatabase User;
    private DatabaseReference MyRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button signOut = findViewById(R.id.nav_signOut);

        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        User = FirebaseDatabase.getInstance();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        }
        else if (id == R.id.listings_button)
        {
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();

            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            newFragment = new listingPage();
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
//            Intent intent = new Intent(MainActivity.this, listingPage.class);
//            startActivity(intent);


        }
        else if (id == R.id.history_button)
        {
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.newRide_button)
        {
            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();

            Fragment newFragment;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            newFragment = new newRideShare();
            transaction.replace(R.id.fragment, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

//          Intent intent = new Intent(MainActivity.this, newRideShare.class);
//          startActivity(intent);

        }
        /*else if (id == R.id.signOut_button)
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


}
