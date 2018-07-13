package com.mobile.a21line.Bid;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Bid_Analysis_Listitem implements Comparable<Bid_Analysis_Listitem>{

    String range;
    String average;
    String count;

    int sortType;
    int orderBy;

    public Bid_Analysis_Listitem(String range, String average, String count) {
        this.range = range;
        this.average = average;
        this.count = count;
        this.sortType = 1;
        this.orderBy = 1;
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

    public void setSortType(int sortType) {
        this.sortType = sortType;
        this.orderBy = 1;
    }

    public void setOrderBy() {
        this.orderBy = orderBy * -1;
    }

    @Override
    public int compareTo(@NonNull Bid_Analysis_Listitem o) {
        int returnVal = 0;
        if (this.sortType == 1) {
            if(parseDouble(this.range) > parseDouble(o.getRange())){
                returnVal = -1;
            }else if(parseDouble(this.range) < parseDouble(o.getRange())){
                returnVal = 1;
            }else{
                returnVal = 0;
            }

            Log.d("Sort1", sortType + " // " + orderBy + " // " + parseDouble(this.range) + " // " + parseDouble(o.getRange()));
        } else if (this.sortType == 2) {
            if(Double.parseDouble(this.average.substring(0, this.average.length() - 1)) > Double.parseDouble(o.getAverage().substring(0, o.getAverage().length() - 1))){
                returnVal = -1;
            }else if(Double.parseDouble(this.average.substring(0, this.average.length() - 1)) < Double.parseDouble(o.getAverage().substring(0, o.getAverage().length() - 1))){
                returnVal = 1;
            }else{
                returnVal = 0;
            }
            Log.d("Sort2", sortType + " // " + orderBy + " // " + Double.parseDouble(this.average.substring(0, this.average.length() - 1)) + " // " + Double.parseDouble(o.getAverage().substring(0, o.getAverage().length() - 1)));
        } else {
            if(Integer.parseInt(this.count) > Integer.parseInt(o.getCount())){
                returnVal = -1;
            }else if(Integer.parseInt(this.count) < Integer.parseInt(o.getCount())){
                returnVal = 1;
            }else{
                returnVal = 0;
            }
            Log.d("Sort3", sortType + " // " + orderBy + " // " + Integer.parseInt(this.count) + " // " + Integer.parseInt(o.getCount()));
        }

        returnVal *= orderBy;

        return returnVal;
    }

    private double parseDouble(String str){
        String[] arrStr = str.split(" ");
        return Double.parseDouble(arrStr[0]);
    }
}
