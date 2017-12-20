package edu.ucsb.cs.cs184.npoon.mobshare;

/**
 * Created by Julio on 12/9/2017.
 */

public class listingItem {

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
}
