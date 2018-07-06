package com.mobile.a21line.Library.BidablePriceFunction;

import com.mobile.a21line.SaveSharedPreference;

/**
 * Created by kwonhong on 2018-07-05.
 */

public class KHNP {

    public String getLimit_1(long money){

        String result = "";

        if(money > 0 ){

            if(money < 100000000){
                result = "2억 미만";
            }else{
                if(money < 500000000 ){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 1000000000){
                        result = "10억 미만";
                    }else{
                        if(money < 3000000000l){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
                            if(money < 4500000000l){
                                result = "30억 미만";
                            }else{
                                if(money < 114000000000l){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money/1.5));
                                }else{
                                    if(money < 25000000000l){
                                        result = "76억 미만";
                                    }else{
                                        if(money < 750000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money/2.5));
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

        return "[" + result + "]";
    }


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
                        if(money < 700000000){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
                            if(money < 1050000000){
                                result = "7억 미만";
                            }else{
                                if(money < 7500000000l ){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.5));
                                }else{
                                    if(money < 10000000000l){
                                        result = "50억 미만";
                                    }else{
                                        if(money < 20000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
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

        return "[" + result + "]";
    }



    public String getLimit_3(long money){

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
                        if(money < 700000000){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
                            if(money < 1050000000){
                                result = "7억 미만";
                            }else{
                                if(money < 7500000000l ){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.5));
                                }else{
                                    if(money < 10000000000l){
                                        result = "50억 미만";
                                    }else{
                                        if(money < 20000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
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

        return "[" + result + "]";
    }

}
