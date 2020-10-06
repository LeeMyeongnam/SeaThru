package com.example.seathru;

/**
 * Created by 이명남 on 2018-05-10.
 */

public class Listviewitem {
    private int icon;
    private String guname;
    private String staname;
    private String phone;
    private double lat;
    private double lon;
    public int getIcon(){return icon;}
    public String getGuName(){return guname;}
    public String getStaName(){return staname;}
    public String getPhone(){return phone;}
    public double getLon(){return lon;}
    public double getLat(){return lat;}
    public Listviewitem(int icon, String guname, String staname, String phone, double lat, double lon){
        this.icon = icon;
        this.guname = guname;
        this.staname = staname;
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
    }
}
