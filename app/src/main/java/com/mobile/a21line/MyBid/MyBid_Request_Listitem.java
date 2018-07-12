package com.mobile.a21line.MyBid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class MyBid_Request_Listitem {


    String bidNo;
    String bidTitle;
    String orderName;
    String bidDate;
    String bidPrice;
    Boolean progress;

    public String getBidNo() {
        return bidNo;
    }

    public void setBidNo(String bidNo) {
        this.bidNo = bidNo;
    }

    public String getBidTitle() {
        return bidTitle;
    }

    public void setBidTitle(String bidTitle) {
        this.bidTitle = bidTitle;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }


    public Boolean getProgress() {
        return progress;
    }

    public void setProgress(Boolean progress) {
        this.progress = progress;
    }

    public MyBid_Request_Listitem(String bidNo, String bidTitle, String orderName, String bidDate, String bidPrice,Boolean progress) {
        this.bidNo = bidNo;
        this.bidTitle = bidTitle;
        this.orderName = orderName;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
        this.progress = progress;
    }
}
