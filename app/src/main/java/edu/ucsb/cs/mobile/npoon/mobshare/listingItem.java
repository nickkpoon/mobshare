package edu.ucsb.cs.mobile.npoon.mobshare;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Julio on 12/9/2017.
 */
@SuppressLint("ParcelCreator")

public class listingItem implements Parcelable, Comparable<listingItem> {

    private String Name;
    private String Trip_Type;
    private String Price;
    private String Depart_Date;
    private String Destination;
    private String Phone_Number;

    private String Depart_Time;
    private String Return_Date;
    private String Return_Time;

    public listingItem() {
    }

    public listingItem(String Name, String Trip_Type, String Price, String Depart_Date, String Destination, String Phone_Number, String Depart_Time, String Return_Date, String Return_Time) {
        this.Name = Name;
        this.Trip_Type = Trip_Type;
        this.Price = Price;
        this.Depart_Date = Depart_Date;
        this.Phone_Number = Phone_Number;
        this.Destination = Destination;
        this.Depart_Time = Depart_Time;
        this.Return_Time = Return_Time;
        this.Return_Date = Return_Date;
    }

    public String getName() {
        return Name;
    }

    public String getTrip_Type() {
        return Trip_Type;
    }

    public String getPrice() {
        return Price;
    }

    public String getDepart_Date() {
        return Depart_Date;
    }

    public String getDestination() {return Destination;}
    public String getPhone_Number(){return Phone_Number;}
    public String getDepart_Time() {return Depart_Time;}

    public String getReturn_Date() {
        return Return_Date;
    }

    public String getReturn_Time() {
        return Return_Time;
    }

    protected listingItem(Parcel in) {
        Name = in.readString();
        Trip_Type = in.readString();
        Price = in.readString();
        Depart_Date = in.readString();
        Destination = in.readString();
        Phone_Number = in.readString();
        Depart_Time = in.readString();
        Return_Date = in.readString();
        Return_Time = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Trip_Type);
        dest.writeString(Price);
        dest.writeString(Depart_Date);
        dest.writeString(Destination);
        dest.writeString(Phone_Number);
        dest.writeString(Depart_Time);
        dest.writeString(Return_Time);
        dest.writeString(Return_Date);
    }

    public int compareTo(listingItem item){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try {
            Date d = dateFormat.parse(getDepart_Date());
            Date date = dateFormat.parse(item.getDepart_Date());
            //Log.d("Date: ", d.toString());
            return date.compareTo(d);
        }
        catch(Exception e) {
            return 0;
        }

    }

}