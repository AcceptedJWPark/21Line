package com.mobile.a21line;

import com.squareup.otto.Bus;

/**
 * Created by kwonhong on 2018-08-03.
 */

public class AddMemoEvent extends Bus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private AddMemoEvent(){

    }
}
