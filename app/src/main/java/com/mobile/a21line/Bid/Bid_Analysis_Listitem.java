package com.mobile.a21line.Bid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Bid_Analysis_Listitem {

    String range;
    String average;
    String count;

    public Bid_Analysis_Listitem(String range, String average, String count) {
        this.range = range;
        this.average = average;
        this.count = count;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
