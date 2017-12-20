package edu.ucsb.cs.cs184.npoon.mobshare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


public class listingPage extends Fragment {
    //private static final String TAG = MyActivity.class.getName();
    private List<listingItem> listItems;
    private int dest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_listingspage, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            dest = bundle.getInt("Dest");
            listItems = bundle.getParcelableArrayList("CardList");
            //Log.d("GOT TO FRAGMENT2!", listItems.get(0).getDate());

            Log.d(TAG, "CardList: ");
        }

        //listItems = savedInstanceState.getParcelableArrayList("")


        //initializeData(dest);
        View v = getView();
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(listingPage.this.getActivity().getApplicationContext()));

        listingsAdapter adapter = new listingsAdapter(listItems);
        recyclerView.setAdapter(adapter);


    }


    private void initializeData(int Destination) {
        listItems = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference MyRef = db.getReference("rideShare");
        Toast.makeText(getActivity(), "LA BUNDLE", Toast.LENGTH_SHORT).show();
       /* MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("FB item PULL", "PULLED!");

                    listingItem newItem = snapshot.getValue(listingItem.class);

                    String NameValue = newItem.getName();
                    Log.d("NAME", NameValue);

                    String Price = newItem.getPrice();
                    String Type = newItem.getTripType();
                    String Date = newItem.getDate();
                    Log.d("DATE:  ", Date);

                    String Destination = "LA";
                    String Phone = newItem.getPhone();

                    listItems.add(newItem);

                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG , "Failed to read value.",error.toException());
            }
        });*/




        switch (Destination)
        {

            case 0:
                 MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //Log.d("FB item PULL", "PULLED!");

                    listingItem newItem = snapshot.getValue(listingItem.class);

                    //String NameValue = newItem.getName();
                    //Log.d("NAME", NameValue);

                    //String Price = newItem.getPrice();
                    //String Type = newItem.getTripType();
                    //String Date = newItem.getDate();
                    //Log.d("DATE:  ", Date);

                    //String Destination = "LA";
                    //String Phone = newItem.getPhone();

                    listItems.add(newItem);

                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG , "Failed to read value.",error.toException());
            }
        });
                 break;

            case 1:
                Toast.makeText(getActivity(), "SF BUNDLE", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <= 10; i++) {
                    listingItem newItem = new listingItem(
                            "USERNAME" + (i + 1), "One WAY", "$" + (i + 100), (i) + "/" + (i) + "/" + (i), "SF", "1234567891", "D","D", "D");
                    listItems.add(newItem);
                    Log.d("hmm", "Username");

                }
                break;

            case 2:
                Toast.makeText(getActivity(), "SAC BUNDLE", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <= 10; i++) {
                    listingItem newItem = new listingItem(
                            "USERNAME" + (i + 1), "One WAY", "$" + (i + 100), (i) + "/" + (i) + "/" + (i), "SF", "1234567891", "D","D", "D");
                    listItems.add(newItem);
                }
                break;

            case 3:
                Toast.makeText(getActivity(), "SD BUNDLE", Toast.LENGTH_SHORT).show();
                for (int i = 0; i <= 10; i++) {
                    listingItem newItem = new listingItem(
                            "USERNAME" + (i + 1), "One WAY", "$" + (i + 100), (i) + "/" + (i) + "/" + (i), "SF", "1234567891", "D","D", "D");
                    listItems.add(newItem);
                }
                break;

        }
    }

}

