package com.mobile.a21line.MyBid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class MyBid_Listitem {

    String groupTitle;
    String bidCount;

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

    public MyBid_Listitem(String groupTitle, String bidCount) {
        this.groupTitle = groupTitle;
        this.bidCount = bidCount;
    }
}
