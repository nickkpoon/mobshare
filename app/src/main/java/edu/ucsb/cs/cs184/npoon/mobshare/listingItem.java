package edu.ucsb.cs.cs184.npoon.mobshare;

/**
 * Created by Julio on 12/9/2017.
 */

public class listingItem {

    private String username;
    private String tripType;
    private String price;
    private String date;

    public listingItem(String username, String tripType, String price, String date) {
        this.username = username;
        this.tripType = tripType;
        this.price = price;
        this.date = date;
    }

    public String getUsername() {
        return username;
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
}
