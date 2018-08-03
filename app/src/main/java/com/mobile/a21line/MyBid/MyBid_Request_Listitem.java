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
    String sendDate;
    String sendMoney;
    String MemMemo;
    String Memo;
    String iBidCode;
    String sendPercent;
    String finalDate;
    int bidState;
    boolean chkTuchal;
    boolean chkMoney;
    boolean chkRegist;
    boolean mybidClicked;
    boolean hasMemo;

    public MyBid_Request_Listitem(String bidNo, String bidTitle, String orderName, String bidDate, String bidPrice, String sendDate, String sendMoney, String memMemo, String memo, boolean chkTuchal, boolean chkMoney, boolean chkRegist, String iBidCode, String sendPercent, String finalDate, int bidState, boolean mybidClicked) {
        this.bidNo = bidNo;
        this.bidTitle = bidTitle;
        this.orderName = orderName;
        this.bidDate = bidDate;
        this.bidPrice = bidPrice;
        this.sendDate = sendDate;
        this.sendMoney = sendMoney;
        MemMemo = memMemo;
        Memo = memo;
        this.chkTuchal = chkTuchal;
        this.chkMoney = chkMoney;
        this.chkRegist = chkRegist;
        this.iBidCode = iBidCode;
        this.sendPercent = sendPercent;
        this.finalDate = finalDate;
        this.bidState = bidState;
        this.mybidClicked = mybidClicked;
        this.hasMemo = !memo.isEmpty();
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

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(String sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getMemMemo() {
        return MemMemo;
    }

    public void setMemMemo(String memMemo) {
        MemMemo = memMemo;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public boolean isChkTuchal() {
        return chkTuchal;
    }

    public void setChkTuchal(boolean chkTuchal) {
        this.chkTuchal = chkTuchal;
    }

    public boolean isChkMoney() {
        return chkMoney;
    }

    public void setChkMoney(boolean chkMoney) {
        this.chkMoney = chkMoney;
    }

    public boolean isChkRegist() {
        return chkRegist;
    }

    public void setChkRegist(boolean chkRegist) {
        this.chkRegist = chkRegist;
    }

    public String getiBidCode(){ return this.iBidCode; }

    public String getSendPercent(){ return this.sendPercent; }

    public String getFinalDate() { return this.finalDate; }

    public int getBidState() { return this.bidState; }

    public boolean isMybidClicked(){
        return mybidClicked;
    }

    public void setMybidClicked(boolean mybidClicked){
        this.mybidClicked = mybidClicked;
    }

    public boolean hasMemo(){
        return this.hasMemo;
    }

    public void setHasMemo(boolean hasMemo){
        this.hasMemo = hasMemo;
    }
}
