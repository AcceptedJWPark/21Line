package com.mobile.a21line.CustomerService;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Qna_Listitem {

    String qnaNumber;
    String qnaTitle;
    String qnaDate;
    String qnaGGroup;
    String qnaContent;
    String qnaComName;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    String Code;

    public String getQnaContent() {
        return qnaContent;
    }

    public void setQnaContent(String qnaContent) {
        this.qnaContent = qnaContent;
    }

    public String getQnaComName() {
        return qnaComName;
    }

    public void setQnaComName(String qnaComName) {
        this.qnaComName = qnaComName;
    }

    public String getQnaGGroup() {
        return qnaGGroup;
    }

    public void setQnaGGroup(String qnaGGroup) {
        this.qnaGGroup = qnaGGroup;
    }

    boolean qnaProgress;

    public Qna_Listitem(String qnaNumber, String qnaTitle, String qnaDate, boolean qnaProgress, String qnaGGroup, String qnaComName, String qnaContent, String Code) {
        this.qnaNumber = qnaNumber;
        this.qnaTitle = qnaTitle;
        this.qnaDate = qnaDate;
        this.qnaProgress = qnaProgress;
        this.qnaGGroup = qnaGGroup;
        this.qnaContent = qnaContent;
        this.qnaComName = qnaComName;
        this.Code = Code;
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
