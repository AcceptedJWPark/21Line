package com.mobile.a21line.Library.BidablePriceFunction;

import com.mobile.a21line.SaveSharedPreference;

/**
 * Created by kwonhong on 2018-07-05.
 */

public class KORAIL {

    /*
    한국철도공사 2017. 04. 25
    
    일반
    100억 이상 5배
    50억 이상 3배
    10억 이상 2배
    3억 이상 1배
    2억 이상 0.5배
    */
    public String getLimit_1(long money){

        String result = "";

        if(money > 0 ){

            if(money < 100000000){
                result = "2억 미만";
            }else{
                if(money < 150000000){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 300000000){
                        result = "3억 미만";
                    }else{
                        if(money < 1000000000){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money));
                        }else{
                            if(money < 2000000000){
                                result = "10억 미만";
                            }else{
                                if(money < 6000000000l){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
                                }else{
                                    if(money < 9000000000l){
                                        result = "30억 미만";
                                    }else{
                                        if(money < 30000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 3));
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
                    }
                }
            }
        }
        return "[" + result + "]";
    }

    /*
    전문
    100억 이상 5배
    30억 이상 3배
    10억 이상 2배
    3억 이상 1.5배
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
                    if(money < 450000000){
                        result = "3억 미만";
                    }else{
                        if(money < 1500000000){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.5));
                        }else{
                            if(money < 2000000000){
                                result = "10억 미만";
                            }else{
                                if(money < 6000000000l){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
                                }else{
                                    if(money < 9000000000l){
                                        result = "30억 미만";
                                    }else{
                                        if(money < 30000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 3));
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
                    }
                }
            }
        }
        return "[" + result + "]";
    }

    /*
    전통소
    100억 이상 3배
    10억 이상 2배
    3억 이상 1.5배
    0.8억 이상 0.5배
    */
    public String getLimit_3(long money){

        String result = "";

        if(money > 0 ){

            if(money < 40000000){
                result = "0.8억 미만";
            }else{
                if(money < 150000000){
                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 0.5));
                }else{
                    if(money < 450000000){
                        result = "3억 미만";
                    }else{
                        if(money < 1500000000){
                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 1.5));
                        }else{
                            if(money < 2000000000){
                                result = "10억 미만";
                            }else{
                                if(money < 20000000000l){
                                    result = SaveSharedPreference.toNumFormat(String.valueOf(money / 2));
                                }else{
                                    if(money < 30000000000l){
                                        result = "300억 미만";
                                    }else{
                                        if(money < 90000000000l){
                                            result = SaveSharedPreference.toNumFormat(String.valueOf(money / 3));
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
