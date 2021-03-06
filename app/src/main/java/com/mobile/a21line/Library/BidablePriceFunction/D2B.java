package com.mobile.a21line.Library.BidablePriceFunction;

import com.mobile.a21line.SaveSharedPreference;

/**
 * Created by kwonhong on 2018-07-05.
 * 국방부 2017.08.01
 */

public class D2B {

    public D2B(){
    }

    /*
국방부 2017.08.01

일반
100억 이상 3배
50억 이상 2배
10억 이상 1배
2억 이상 0.5배
*/
    public String getLimit_1(long money){
        String result = "";
        if(money > 0 ){

            if(money < 100000000){
                result = "2억 미만";
            }else{
                if(money < 1000000000){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money/0.5));
                }else{
                    if(money < 5000000000l){
                        result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                    }else{
                        if(money < 10000000000l){
                            result = "50억 미만";
                        }else{
                            if(money < 20000000000l){
                                result = SaveSharedPreference.toNumFormat(String.valueOf(money/2));
                            }else{
                                if(money < 30000000000l){
                                    result = "100억 미만";
                                }else{
                                    if(money < 90000000000l){
                                        result = SaveSharedPreference.toNumFormat(String.valueOf(money/3));
                                    }else{
                                        result = "별도심사";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "[" + result + "]";
    }
    /*
    전문
    10억 이상 1배
    1억 이상 0.5배
    */
    public String getLimit_2(long money) {
        String result = "";
        if(money > 0 ){

            if(money < 50000000){
                result = "1억 미만";
            }else{
                if(money < 500000000){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else {
                    if(money < 1000000000){
                        result = "10억 미만";
                    } else {
                        if(money <5000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        } else {
                            result = "별도심사";
                        }
                    }
                }
            }
        }
        return "[" + result + "]";
    }
    /*
    전통소
    전문과 동일
    */
    public String getLimit_3(long money){
        String result = "";
        if(money > 0 ){

            if(money < 50000000){
                result = "1억 미만";
            }else{
                if(money < 500000000){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else {
                    if(money < 1000000000){
                        result = "10억 미만";
                    } else {
                        if(money <5000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        } else {
                            result = "별도심사";
                        }
                    }
                }
            }
        }
        return "[" + result + "]";
    }



}
