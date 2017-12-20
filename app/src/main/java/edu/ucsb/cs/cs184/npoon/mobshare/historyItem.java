package edu.ucsb.cs.cs184.npoon.mobshare;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Julio on 12/9/2017.
 */
@SuppressLint("ParcelCreator")

public class historyItem implements Parcelable {

//    private String Name;
    private String Trip_TypeHist;
    private String PriceHist;
    private String Depart_DateHist;
    private String DestinationHist;
//    private String Phone_Number;

    private String Depart_TimeHist;
    private String Return_DateHist;
    private String Return_TimeHist;

    public historyItem() {
    }

    public historyItem(String Trip_Type, String Price, String Depart_Date, String Destination, String Depart_Time, String Return_Date, String Return_Time) {
//        this.Name = Name;
        this.Trip_TypeHist = Trip_Type;
        this.PriceHist = Price;
        this.Depart_DateHist = Depart_Date;
//        this.Phone_Number = Phone_Number;
        this.DestinationHist = Destination;
        this.Depart_TimeHist = Depart_Time;
        this.Return_TimeHist = Return_Time;
        this.Return_DateHist = Return_Date;
    }

//    public String getName() {
//        return Name;
//    }

    public String getTrip_TypeHist() {
        return Trip_TypeHist;
    }

    public String getPriceHist() {
        return PriceHist;
    }

    public String getDepart_DateHist() {
        return Depart_DateHist;
    }

    public String getDestinationHist() {return DestinationHist;}
//    public String getPhone_Number(){return Phone_Number;}
    public String getDepart_TimeHist() {return Depart_TimeHist;}

    public String getReturn_DateHist() {
        return Return_DateHist;
    }

    public String getReturn_TimeHist() {
        return Return_TimeHist;
    }

    protected historyItem(Parcel in) {
//        Name = in.readString();
        Trip_TypeHist = in.readString();
        PriceHist = in.readString();
        Depart_DateHist = in.readString();
        DestinationHist = in.readString();
//        Phone_Number = in.readString();
        Depart_TimeHist = in.readString();
        Return_DateHist = in.readString();
        Return_TimeHist = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(Name);
        dest.writeString(Trip_TypeHist);
        dest.writeString(PriceHist);
        dest.writeString(Depart_DateHist);
        dest.writeString(DestinationHist);
//        dest.writeString(Phone_Number);
        dest.writeString(Depart_TimeHist);
        dest.writeString(Return_TimeHist);
        dest.writeString(Return_DateHist);
    }


}