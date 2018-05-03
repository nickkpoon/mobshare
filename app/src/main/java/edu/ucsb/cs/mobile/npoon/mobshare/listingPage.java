package edu.ucsb.cs.mobile.npoon.mobshare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.ContentValues.TAG;


public class listingPage extends Fragment implements listingsAdapter.OnItemClickListener{
    //private static final String TAG = MyActivity.class.getName();
    private List<listingItem> listItems;
    private int dest;

    private listingsAdapter.OnItemClickListener listener;


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


        View v = getView();
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(listingPage.this.getActivity().getApplicationContext()));

        listingsAdapter adapter = new listingsAdapter(this, listItems, getActivity());
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onItemClicked(View v) {
        dialogFragmentCard df= new dialogFragmentCard();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference MyRef = db.getReference("Users");
        TextView name = v.findViewById(R.id.Name);
        final String Name = name.getText().toString().substring(8);
        TextView number = v.findViewById(R.id.Phone);
        final String Phone = number.getText().toString().substring(8);
        //Log.d("Name: ", Name);
       // Log.d("Phone Number:", Phone);
        MyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String Username = snapshot.child("Name").getValue(String.class);
                    String PhoneNumber = snapshot.child("Phone Number").getValue(String.class);
                    Integer rides = snapshot.child("Rides Given").getValue(Integer.class);
                    String Email = snapshot.child("UserName").getValue(String.class);
                    if (Username.equals(Name) && PhoneNumber.equals(Phone)){
                        //Integer rides = dataSnapshot.child("Rides Given").getValue(Integer.class);
                       // String Email = dataSnapshot.child("Username").getValue(String.class);
                        Bundle args = new Bundle();
                        args.putString("Phone", Phone);
                        args.putString("Username", Email);
                        args.putString("RealName", Name);
                        args.putInt("Rides", rides);
                        dialogFragmentCard df= new dialogFragmentCard();
                        df.setArguments(args);
                        df.show(getFragmentManager(), "Dialog");

                    }
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

