package com.mobile.a21line.CustomerService;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Notice_Listitem {

    String noticeNumber;
    String noticeTitle;
    String noticeDate;

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

    public Notice_Listitem(String noticeNumber, String noticeTitle, String noticeDate, String noticeContent) {
        this.noticeNumber = noticeNumber;
        this.noticeTitle = noticeTitle;
        this.noticeDate = noticeDate;
        this.noticeContent = noticeContent;
    }


}