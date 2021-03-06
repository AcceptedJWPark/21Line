package com.mobile.a21line.Bid;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Bid_Listitem {

    String bidNo;
    String bidTitle;
    String orderName;
    String bidDate;
    String bidPrice;
    String iBidCode;
    boolean mybidClicked;
    boolean hasMemo;
    int bidState;


    boolean isNewBid;

    public boolean isNewBid() {
        return isNewBid;
    }

    public void setNewBid(boolean newBid) {
        isNewBid = newBid;
    }

    public Boolean getMybidClicked() {
        return mybidClicked;
    }

    public void setMybidClicked(Boolean mybidClicked) {
        this.mybidClicked = mybidClicked;
    }

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

    public void setiBidCode(String iBidCode){ this.iBidCode = iBidCode; }

    public String getiBidCode() { return iBidCode; }

    public void setBidState(int bidState){ this.bidState = bidState; }

    public int getBidState() { return bidState; }

    public boolean hasMemo(){
        return this.hasMemo;
    }

    public void setHasMemo(boolean hasMemo){
        this.hasMemo = hasMemo;
    }


    public Bid_Listitem(String bidNo, String bidTitle, String orderName, String bidDate, String bidPrice, boolean mybidClicked, String iBidCode, int bidState, boolean hasMemo) {
        this.bidNo = bidNo;
        this.bidTitle = bidTitle;
        this.orderName = orderName;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
        this.mybidClicked = mybidClicked;
        this.hasMemo = hasMemo;
        this.iBidCode = iBidCode;
        this.bidState = bidState;
    }

    public Bid_Listitem(String bidNo, String bidTitle, String orderName, String bidDate, String bidPrice, boolean mybidClicked, String iBidCode, int bidState, boolean hasMemo, boolean isNewBid) {
        this.bidNo = bidNo;
        this.bidTitle = bidTitle;
        this.orderName = orderName;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
        this.mybidClicked = mybidClicked;
        this.hasMemo = hasMemo;
        this.iBidCode = iBidCode;
        this.bidState = bidState;
        this.isNewBid = isNewBid;
    }

}
