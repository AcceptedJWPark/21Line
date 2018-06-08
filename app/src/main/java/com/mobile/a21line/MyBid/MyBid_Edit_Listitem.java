package com.mobile.a21line.MyBid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class MyBid_Edit_Listitem {

    String groupTitle;
    String bidCount;
    String rDate;

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getBidCount() {
        return bidCount;
    }

    public void setBidCount(String bidCount) {
        this.bidCount = bidCount;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public MyBid_Edit_Listitem(String groupTitle, String bidCount, String rDate) {
        this.groupTitle = groupTitle;
        this.bidCount = bidCount;
        this.rDate = rDate;
    }
}
