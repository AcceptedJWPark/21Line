package com.mobile.a21line.MyBid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class MyBid_Listitem {

    String groupTitle;
    String bidCount;
    String regDate;
    int GCode;

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

    public int getGCode(){
        return GCode;
    }

    public void setGCode(int GCode){
        this.GCode = GCode;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate){
        this.regDate = regDate;
    }



    public MyBid_Listitem(String groupTitle, String bidCount, int GCode, String regDate) {
        this.groupTitle = groupTitle;
        this.bidCount = bidCount;
        this.GCode = GCode;
        this.regDate = regDate;
    }
}
