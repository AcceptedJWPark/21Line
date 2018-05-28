package com.mobile.a21line.Result;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Result_Detail_CompanyList_Parent_Listitem {

    String num;
    String company;
    String price;
    String percent;

    public Result_Detail_CompanyList_Parent_Listitem(String num, String company, String price, String percent) {
        this.num = num;
        this.company = company;
        this.price = price;
        this.percent = percent;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
