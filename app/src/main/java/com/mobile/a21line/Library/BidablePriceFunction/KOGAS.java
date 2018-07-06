package com.mobile.a21line.Library.BidablePriceFunction;

import com.mobile.a21line.SaveSharedPreference;

/**
 * Created by kwonhong on 2018-07-05.
 */

public class KOGAS {
    /*
한국가스공사 2017. 04. 05

일반
100억 이상 5배
50억 이상 2배(3년)
10억 이상 1배
2억 이상 0.5배
*/
    public String getLimit_1(long money){

        String result = "";

        if(money > 0 ){

            if(money < 100000000){
                result = "2억 미만";
            }else{
                if(money < 500000000 ){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 2000000000){
                        result = "10억 미만";
                    }else{
                        if(money < 20000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
                        }else{
                            if(money < 50000000000l){
                                result = "100억 미만";
                            }else{
                                if(money < 150000000000l){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 5));
                                }else{
                                    result = "별도심사";
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
3억 이상 1배
1억 이상 0.5배
*/

    public String getLimit_2(long money){

        String result = "";

        if(money > 0 ){

            if(money < 50000000){
                result = "1억 미만";
            }else{
                if(money < 150000000 ){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 300000000){
                        result = "3억 미만";
                    }else{
                        if(money < 5000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
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
3억 이상 1배
8천 이상 0.5배
*/

    public String getLimit_3(long money){

        String result = "";

        if(money > 0 ){

            if(money < 40000000){
                result = "8천만원 미만";
            }else{
                if(money < 150000000 ){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 300000000){
                        result = "3억 미만";
                    }else{
                        if(money < 5000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
                            result = "별도심사";
                        }
                    }
                }
            }
        }

        return "[" + result + "]";
    }
}
