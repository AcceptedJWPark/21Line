package com.mobile.a21line.Result;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Result_Listitem {

    String bidNo;
    String bidTitle;
    String orderName;
    String firstComp;
    String resultPrice;
    Boolean mybidClicked;
    Boolean resultFailed;
    String failedReason;


    public Boolean getResultFailed() {
        return resultFailed;
    }

    public void setResultFailed(Boolean resultFailed) {
        this.resultFailed = resultFailed;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
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

    public String getFirstComp() {
        return firstComp;
    }

    public void setFirstComp(String firstComp) {
        this.firstComp = firstComp;
    }

    public String getResultPrice() {
        return resultPrice;
    }

    public void setResultPrice(String resultPrice) {
        this.resultPrice = resultPrice;
    }

    public Boolean getMybidClicked() {
        return mybidClicked;
    }

    public void setMybidClicked(Boolean mybidClicked) {
        this.mybidClicked = mybidClicked;
    }

    public Result_Listitem(String bidNo, String bidTitle, String orderName, String firstComp, String resultPrice, Boolean mybidClicked, Boolean resultFailed, String failedReason) {
        this.bidNo = bidNo;
        this.bidTitle = bidTitle;
        this.orderName = orderName;
        this.firstComp = firstComp;
        this.resultPrice = resultPrice;
        this.mybidClicked = mybidClicked;
        this.resultFailed = resultFailed;
        this.failedReason = failedReason;
    }





}