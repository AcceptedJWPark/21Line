package com.mobile.a21line.CustomerService;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Notice_Listitem {

    String noticeNumber;
    String noticeTitle;
    String noticeDate;
    String noticeCode;
    String noticeFile1;
    String noticeFile2;
    String noticeFile3;
    String noticeFile4;

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeFile1() {
        return noticeFile1;
    }

    public void setNoticeFile1(String noticeFile1) {
        this.noticeFile1 = noticeFile1;
    }

    public String getNoticeFile2() {
        return noticeFile2;
    }

    public void setNoticeFile2(String noticeFile2) {
        this.noticeFile2 = noticeFile2;
    }

    public String getNoticeFile3() {
        return noticeFile3;
    }

    public void setNoticeFile3(String noticeFile3) {
        this.noticeFile3 = noticeFile3;
    }

    public String getNoticeFile4() {
        return noticeFile4;
    }

    public void setNoticeFile4(String noticeFile4) {
        this.noticeFile4 = noticeFile4;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    String noticeContent;

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public Notice_Listitem(String noticeNumber, String noticeTitle, String noticeDate, String noticeContent, String noticeCode, String noticeFile1, String noticeFile2, String noticeFile3, String noticeFile4) {
        this.noticeNumber = noticeNumber;
        this.noticeTitle = noticeTitle;
        this.noticeDate = noticeDate;
        this.noticeContent = noticeContent;
        this.noticeCode = noticeCode;
        this.noticeFile1 = noticeFile1;
        this.noticeFile2 = noticeFile2;
        this.noticeFile3 = noticeFile3;
        this.noticeFile4 = noticeFile4;

    }


}
