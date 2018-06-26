package com.mobile.a21line.CustomerService;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Qna_Listitem {

    String qnaNumber;
    String qnaTitle;
    String qnaDate;
    boolean qnaProgress;

    public Qna_Listitem(String qnaNumber, String qnaTitle, String qnaDate, boolean qnaProgress) {
        this.qnaNumber = qnaNumber;
        this.qnaTitle = qnaTitle;
        this.qnaDate = qnaDate;
        this.qnaProgress = qnaProgress;
    }

    public String getQnaNumber() {
        return qnaNumber;
    }

    public void setQnaNumber(String qnaNumber) {
        this.qnaNumber = qnaNumber;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }

    public void setQnaTitle(String qnaTitle) {
        this.qnaTitle = qnaTitle;
    }

    public String getQnaDate() {
        return qnaDate;
    }

    public void setQnaDate(String qnaDate) {
        this.qnaDate = qnaDate;
    }

    public boolean isQnaProgress() {
        return qnaProgress;
    }

    public void setQnaProgress(boolean qnaProgress) {
        this.qnaProgress = qnaProgress;
    }
}
