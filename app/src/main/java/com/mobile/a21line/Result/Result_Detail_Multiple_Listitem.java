package com.mobile.a21line.Result;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Result_Detail_Multiple_Listitem {

    String num;
    String price;
    String dev;
    String percent;
    boolean checked;

    public Result_Detail_Multiple_Listitem(String num, String price, String dev, String percent, boolean checked) {
        this.num = num;
        this.price = price;
        this.dev = dev;
        this.percent = percent;
        this.checked = checked;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
