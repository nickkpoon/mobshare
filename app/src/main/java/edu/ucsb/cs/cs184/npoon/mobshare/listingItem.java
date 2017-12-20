package edu.ucsb.cs.cs184.npoon.mobshare;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Julio on 12/9/2017.
 */
@SuppressLint("ParcelCreator")

public class listingItem implements Parcelable {

    private String Name;
    private String tripType;
    private String price;
    private String date;
    private String Destination;
    private String Phone;

    public listingItem() {
    }

    public listingItem(String Name, String tripType, String price, String date, String Destination, String Phone) {
        this.Name = Name;
        this.tripType = tripType;
        this.price = price;
        this.date = date;
        this.Phone = Phone;
        this.Destination = Destination;
    }

    public String getName() {
        return Name;
    }

    public String getTripType() {
        return tripType;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {return Destination;}
    public String getPhone(){return Phone;}

    protected listingItem(Parcel in) {
        Name = in.readString();
        tripType = in.readString();
        price = in.readString();
        date = in.readString();
        Destination = in.readString();
        Phone = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(tripType);
        dest.writeString(price);
        dest.writeString(date);
        dest.writeString(Destination);
        dest.writeString(Phone);
    }


}