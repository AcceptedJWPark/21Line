package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-05-16.
 */

public class BinaryCode {
    String name;
    int code;
    boolean isChecked = false;

    public BinaryCode (String name, int code){
        this.name = name;
        this.code = code;
    }

    public String getName(){
        return this.name;
    }

    public int getCode(){
        return this.code;
    }

    public boolean Contains(int tar){
        int c = this.code & tar;
        if(c > 0)
            return true;
        return false;
    }

    public void setChecked(boolean checked){
        isChecked = checked;
    }

    public boolean isChecked(){
        return isChecked;
    }

}
