package com.mobile.a21line.Library;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Library_BusinessCondition_Listitem {

    String businessTitle;
    String ratio1;
    String ratio2;

    public Library_BusinessCondition_Listitem(String businessTitle, String ratio1, String ratio2) {
        this.businessTitle = businessTitle;
        this.ratio1 = ratio1;
        this.ratio2 = ratio2;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public String getRatio1() {
        return ratio1;
    }

    public void setRatio1(String ratio1) {
        this.ratio1 = ratio1;
    }

    public String getRatio2() {
        return ratio2;
    }

    public void setRatio2(String ratio2) {
        this.ratio2 = ratio2;
    }
}
