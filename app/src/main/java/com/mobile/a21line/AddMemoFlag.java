package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-08-03.
 */

public class AddMemoFlag {
    int position;
    boolean isAdded;
    boolean isMoveGroup;
    String Memo;
    public AddMemoFlag(int position, boolean isAdded, boolean isMoveGroup){
        this.position = position;
        this.isAdded = isAdded;
        this.isMoveGroup = isMoveGroup;
    }

    public int getPosition(){
        return position;
    }

    public boolean isAdded(){
        return isAdded;
    }

    public void setMemo(String Memo){
        this.Memo = Memo;
    }

    public String getMemo(){
        return this.Memo;
    }

    public boolean isMoveGroup(){ return this.isMoveGroup; }
}
