package com.mobile.a21line.Result;

/**
 * Created by Accepted on 2018-05-16.
 */

public class Result_Detail_CompanyList_Child_Listitem {

    String company;
    String companyNo;
    String ceo;


    public Result_Detail_CompanyList_Child_Listitem(String company, String companyNo, String ceo) {
        this.company = company;
        this.companyNo = companyNo;
        this.ceo = ceo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
}
