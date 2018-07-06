package com.mobile.a21line.Library.BidablePriceFunction;

import com.mobile.a21line.SaveSharedPreference;

/**
 * Created by kwonhong on 2018-07-05.
 */

public class MOI {
    /*
행안부 2017. 04. 11

일반
100억 이상 1.8배
50억 이상 1.7배
10억 이상 1배(5년)
3억 이상 0.7 / 0.6배
3억 미만 0.6 / 0.5배
*/

    public String getLimit_1(long money){

        String result = "";

        if(money > 0 ){
            // 7/1 이전 1.8억 0.6배, 7/1 이후 1.5억 0.5배
            if(money < 150000000){
                result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
            }else{ // 7/1 이전 2.1억, 7/1 이후 1.8억
                if(money < 180000000) {
                    result = "3억 미만";
                }else{ // 7/1 이전에는 7억 0.6배, 7/1 이후에는 6억 0.5배
                    if(money < 600000000){
                        result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                    }else{
                        if(money < 1000000000){
                            result = "10억 미만";
                        }else{
                            if(money < 5000000000l){
                                result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                            }else{
                                if(money < 8500000000l){
                                    result = "50억 미만";
                                }else{
                                    if(money < 17000000000l){
                                        result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.7));
                                    }else{
                                        if(money < 18000000000l){
                                            result = "100억 미만";
                                        }else{
                                            if(money < 54000000000l){
                                                result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.8));
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
            }
        }
        return "[" + result + "]";
    }

    public String getLimit_2(long money){

        String result = "";

        if(money > 0 ){
            // 7/1 이전 0.6억 0.6배, 7/1 이후 0.5억 0.5배
            if(money < 50000000){
                result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
            }else{ // 7/1 이전 2.1억, 7/1 이후 1.8억
                if(money < 180000000) {
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.6));
                }else{ // 7/1 이전에는 2.1억, 7/1 이후에는 2.4억
                    if(money < 240000000){
                        result = "3억 미만";
                    }else{
                        if(money < 4000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.8));
                        }else{
                            result = "별도심사";
                        }
                    }
                }
            }
        }
        return "[" + result + "]";
    }

    public String getLimit_3(long money){

        String result = "";

        if(money > 0 ){
            // 7/1 이전 0.9억 0.6배, 7/1 이후 0.75억 0.5배
            if(money < 75000000){
                result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
            }else{
                // 7/1 이전 2.1억, 7/1 이후 1.8억
                if(money < 180000000) {
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.6));
                }else{
                    // 7/1 이전에는 2.1억, 7/1 이후에는 2.4억
                    if(money < 240000000){
                        result = "3억 미만";
                    }else{
                        if(money < 4000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.8));
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
