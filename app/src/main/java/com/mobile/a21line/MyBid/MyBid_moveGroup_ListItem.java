package com.mobile.a21line.MyBid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_moveGroup_ListItem {
    String groupTitle;
    boolean checked;
    int GCode;

    public MyBid_moveGroup_ListItem(String groupTitle, boolean checked, int GCode) {
        this.groupTitle = groupTitle;
        this.GCode = GCode;
        this.checked = checked;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getGCode() {
        return GCode;
    }

    public void setGCode(int GCode) {
        this.GCode = GCode;
    }



}




