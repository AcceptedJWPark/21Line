package com.mobile.a21line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kwonhong on 2018-05-10.
 */

public class BidAreaCode {
    static Map<String, String> mapAreaCode = new HashMap();
    static ArrayList<String> arrayMainAreaName = new ArrayList<>();
    static public void makeAreaCode(){
        mapAreaCode.put("서울 ", "110000");
        mapAreaCode.put("서울 관내", "119999");
        mapAreaCode.put("서울 강남구", "110100");
        mapAreaCode.put("서울 강동구", "110200");
        mapAreaCode.put("서울 강북구", "110300");
        mapAreaCode.put("서울 강서구", "110400");
        mapAreaCode.put("서울 관악구", "110500");
        mapAreaCode.put("서울 광진구", "110600");
        mapAreaCode.put("서울 구로구", "110700");
        mapAreaCode.put("서울 금천구", "110800");
        mapAreaCode.put("서울 노원구", "110900");
        mapAreaCode.put("서울 도봉구", "111000");
        mapAreaCode.put("서울 동대문구", "111100");
        mapAreaCode.put("서울 동작구", "111200");
        mapAreaCode.put("서울 마포구", "111300");
        mapAreaCode.put("서울 서대문구", "111400");
        mapAreaCode.put("서울 서초구", "111500");
        mapAreaCode.put("서울 성동구", "111600");
        mapAreaCode.put("서울 성북구", "111700");
        mapAreaCode.put("서울 송파구", "111800");
        mapAreaCode.put("서울 양천구", "111900");
        mapAreaCode.put("서울 영등포구", "112000");
        mapAreaCode.put("서울 용산구", "112100");
        mapAreaCode.put("서울 은평구", "112200");
        mapAreaCode.put("서울 종로구", "112300");
        mapAreaCode.put("서울 중구", "112400");
        mapAreaCode.put("서울 중랑구", "112500");
        mapAreaCode.put("부산 ", "120000");
        mapAreaCode.put("부산 관내", "129999");
        mapAreaCode.put("부산 강서구", "120100");
        mapAreaCode.put("부산 금정구", "120200");
        mapAreaCode.put("부산 남구", "120300");
        mapAreaCode.put("부산 동구", "120400");
        mapAreaCode.put("부산 동래구", "120500");
        mapAreaCode.put("부산 부산진구", "120600");
        mapAreaCode.put("부산 북구", "120700");
        mapAreaCode.put("부산 사상구", "120800");
        mapAreaCode.put("부산 사하구", "120900");
        mapAreaCode.put("부산 서구", "121000");
        mapAreaCode.put("부산 수영구", "121100");
        mapAreaCode.put("부산 연제구", "121200");
        mapAreaCode.put("부산 영도구", "121300");
        mapAreaCode.put("부산 중구", "121400");
        mapAreaCode.put("부산 해운대구", "121500");
        mapAreaCode.put("부산 기장군", "121600");
        mapAreaCode.put("대구 ", "130000");
        mapAreaCode.put("대구 관내", "139999");
        mapAreaCode.put("대구 남구", "130100");
        mapAreaCode.put("대구 달서구", "130200");
        mapAreaCode.put("대구 동구", "130300");
        mapAreaCode.put("대구 북구", "130400");
        mapAreaCode.put("대구 서구", "130500");
        mapAreaCode.put("대구 수성구", "130600");
        mapAreaCode.put("대구 중구", "130700");
        mapAreaCode.put("대구 달성군", "130800");
        mapAreaCode.put("인천 ", "140000");
        mapAreaCode.put("인천 관내", "149999");
        mapAreaCode.put("인천 계양구", "140100");
        mapAreaCode.put("인천 남구", "140200");
        mapAreaCode.put("인천 남동구", "140300");
        mapAreaCode.put("인천 동구", "140400");
        mapAreaCode.put("인천 부평구", "140500");
        mapAreaCode.put("인천 서구", "140600");
        mapAreaCode.put("인천 연수구", "140700");
        mapAreaCode.put("인천 중구", "140800");
        mapAreaCode.put("인천 강화군", "140900");
        mapAreaCode.put("인천 옹진군", "141000");
        mapAreaCode.put("광주 ", "150000");
        mapAreaCode.put("광주 관내", "159999");
        mapAreaCode.put("광주 광산구", "150100");
        mapAreaCode.put("광주 남구", "150200");
        mapAreaCode.put("광주 동구", "150300");
        mapAreaCode.put("광주 북구", "150400");
        mapAreaCode.put("광주 서구", "150500");
        mapAreaCode.put("대전 ", "160000");
        mapAreaCode.put("대전 관내", "169999");
        mapAreaCode.put("대전 대덕구", "160100");
        mapAreaCode.put("대전 동구", "160200");
        mapAreaCode.put("대전 서구", "160300");
        mapAreaCode.put("대전 유성구", "160400");
        mapAreaCode.put("대전 중구", "160500");
        mapAreaCode.put("울산 ", "170000");
        mapAreaCode.put("울산 관내", "179999");
        mapAreaCode.put("울산 남구", "170100");
        mapAreaCode.put("울산 동구", "170200");
        mapAreaCode.put("울산 북구", "170300");
        mapAreaCode.put("울산 중구", "170400");
        mapAreaCode.put("울산 울주군", "170500");
        mapAreaCode.put("세종 ", "180000");
        mapAreaCode.put("세종 관내", "189999");
        mapAreaCode.put("세종 조치원읍", "180100");
        mapAreaCode.put("세종 연기면", "180200");
        mapAreaCode.put("세종 연동면", "180300");
        mapAreaCode.put("세종 부강면", "180400");
        mapAreaCode.put("세종 금남면", "180500");
        mapAreaCode.put("세종 장군면", "180600");
        mapAreaCode.put("세종 연서면", "180700");
        mapAreaCode.put("세종 전의면", "180800");
        mapAreaCode.put("세종 전동면", "180900");
        mapAreaCode.put("세종 소정면", "181000");
        mapAreaCode.put("세종 반곡동", "181100");
        mapAreaCode.put("세종 소담동", "181200");
        mapAreaCode.put("세종 보람동", "181300");
        mapAreaCode.put("세종 대평동", "181400");
        mapAreaCode.put("세종 가람동", "181500");
        mapAreaCode.put("세종 한솔동", "181600");
        mapAreaCode.put("세종 나성동", "181700");
        mapAreaCode.put("세종 새롬동", "181800");
        mapAreaCode.put("세종 다정동", "181900");
        mapAreaCode.put("세종 어진동", "182000");
        mapAreaCode.put("세종 종촌동", "182100");
        mapAreaCode.put("세종 고운동", "182200");
        mapAreaCode.put("세종 아름동", "182300");
        mapAreaCode.put("세종 도담동", "182400");
        mapAreaCode.put("강원 ", "210000");
        mapAreaCode.put("강원 관내", "219999");
        mapAreaCode.put("강원 강릉시", "210100");
        mapAreaCode.put("강원 동해시", "210200");
        mapAreaCode.put("강원 삼척시", "210300");
        mapAreaCode.put("강원 속초시", "210400");
        mapAreaCode.put("강원 원주시", "210500");
        mapAreaCode.put("강원 춘천시", "210600");
        mapAreaCode.put("강원 태백시", "210700");
        mapAreaCode.put("강원 고성군", "210800");
        mapAreaCode.put("강원 양구군", "210900");
        mapAreaCode.put("강원 양양군", "211000");
        mapAreaCode.put("강원 영월군", "211100");
        mapAreaCode.put("강원 인제군", "211200");
        mapAreaCode.put("강원 정선군", "211300");
        mapAreaCode.put("강원 철원군", "211400");
        mapAreaCode.put("강원 평창군", "211500");
        mapAreaCode.put("강원 홍천군", "211600");
        mapAreaCode.put("강원 화천군", "211700");
        mapAreaCode.put("강원 횡성군", "211800");
        mapAreaCode.put("경기 ", "220000");
        mapAreaCode.put("경기 관내", "229999");
        mapAreaCode.put("경기 고양시", "220100");
        mapAreaCode.put("경기 고양시 덕양구", "220101");
        mapAreaCode.put("경기 고양시 일산동구", "220102");
        mapAreaCode.put("경기 고양시 일산서구", "220103");
        mapAreaCode.put("경기 과천시", "220400");
        mapAreaCode.put("경기 광명시", "220500");
        mapAreaCode.put("경기 광주시", "220600");
        mapAreaCode.put("경기 구리시", "220700");
        mapAreaCode.put("경기 군포시", "220800");
        mapAreaCode.put("경기 김포시", "220900");
        mapAreaCode.put("경기 남양주시", "221000");
        mapAreaCode.put("경기 동두천시", "221100");
        mapAreaCode.put("경기 부천시", "221200");
        mapAreaCode.put("경기 부천시 소사구", "221201");
        mapAreaCode.put("경기 부천시 오정구", "221202");
        mapAreaCode.put("경기 부천시 원미구", "221203");
        mapAreaCode.put("경기 성남시", "221500");
        mapAreaCode.put("경기 성남시 분당구", "221501");
        mapAreaCode.put("경기 성남시 수정구", "221502");
        mapAreaCode.put("경기 성남시 중원구", "221503");
        mapAreaCode.put("경기 수원시", "221800");
        mapAreaCode.put("경기 수원시 권선구", "221801");
        mapAreaCode.put("경기 수원시 영통구", "221802");
        mapAreaCode.put("경기 수원시 장안구", "221803");
        mapAreaCode.put("경기 수원시 팔달구", "221804");
        mapAreaCode.put("경기 시흥시", "222200");
        mapAreaCode.put("경기 안산시", "222300");
        mapAreaCode.put("경기 안산시 단원구", "222301");
        mapAreaCode.put("경기 안산시 상록구", "222302");
        mapAreaCode.put("경기 안성시", "222500");
        mapAreaCode.put("경기 안양시", "222600");
        mapAreaCode.put("경기 안양시 동안구", "222601");
        mapAreaCode.put("경기 안양시 만안구", "222602");
        mapAreaCode.put("경기 양주시", "222800");
        mapAreaCode.put("경기 오산시", "222900");
        mapAreaCode.put("경기 용인시", "223000");
        mapAreaCode.put("경기 용인시 기흥구", "223001");
        mapAreaCode.put("경기 용인시 수지구", "223002");
        mapAreaCode.put("경기 용인시 처인구", "223003");
        mapAreaCode.put("경기 의왕시", "223300");
        mapAreaCode.put("경기 의정부시", "223400");
        mapAreaCode.put("경기 이천시", "223500");
        mapAreaCode.put("경기 파주시", "223600");
        mapAreaCode.put("경기 평택시", "223700");
        mapAreaCode.put("경기 포천시", "223800");
        mapAreaCode.put("경기 하남시", "223900");
        mapAreaCode.put("경기 화성시", "224000");
        mapAreaCode.put("경기 가평군", "224100");
        mapAreaCode.put("경기 양평군", "224200");
        mapAreaCode.put("경기 여주군", "224300");
        mapAreaCode.put("경기 연천군", "224400");
        mapAreaCode.put("경남 ", "230000");
        mapAreaCode.put("경남 관내", "239999");
        mapAreaCode.put("경남 거제시", "230100");
        mapAreaCode.put("경남 김해시", "230200");
        mapAreaCode.put("경남 마산시", "230300");
        mapAreaCode.put("경남 밀양시", "230400");
        mapAreaCode.put("경남 사천시", "230500");
        mapAreaCode.put("경남 양산시", "230600");
        mapAreaCode.put("경남 진주시", "230700");
        mapAreaCode.put("경남 진해시", "230800");
        mapAreaCode.put("경남 창원시", "230900");
        mapAreaCode.put("경남 창원시 마산합포구", "230901");
        mapAreaCode.put("경남 창원시 마산회원구", "230902");
        mapAreaCode.put("경남 창원시 의창구", "230903");
        mapAreaCode.put("경남 창원시 성산구", "230904");
        mapAreaCode.put("경남 창원시 진해구", "230905");
        mapAreaCode.put("경남 통영시", "231000");
        mapAreaCode.put("경남 거창군", "231100");
        mapAreaCode.put("경남 고성군", "231200");
        mapAreaCode.put("경남 남해군", "231300");
        mapAreaCode.put("경남 산청군", "231400");
        mapAreaCode.put("경남 의령군", "231500");
        mapAreaCode.put("경남 창녕군", "231600");
        mapAreaCode.put("경남 하동군", "231700");
        mapAreaCode.put("경남 함안군", "231800");
        mapAreaCode.put("경남 함양군", "231900");
        mapAreaCode.put("경남 합천군", "232000");
        mapAreaCode.put("경북 ", "240000");
        mapAreaCode.put("경북 관내", "249999");
        mapAreaCode.put("경북 경산시", "240100");
        mapAreaCode.put("경북 경주시", "240200");
        mapAreaCode.put("경북 구미시", "240300");
        mapAreaCode.put("경북 김천시", "240400");
        mapAreaCode.put("경북 문경시", "240500");
        mapAreaCode.put("경북 상주시", "240600");
        mapAreaCode.put("경북 안동시", "240700");
        mapAreaCode.put("경북 영주시", "240800");
        mapAreaCode.put("경북 영천시", "240900");
        mapAreaCode.put("경북 포항시", "241000");
        mapAreaCode.put("경북 포항시 남구", "241001");
        mapAreaCode.put("경북 포항시 북구", "241002");
        mapAreaCode.put("경북 고령군", "241200");
        mapAreaCode.put("경북 군위군", "241300");
        mapAreaCode.put("경북 봉화군", "241400");
        mapAreaCode.put("경북 성주군", "241500");
        mapAreaCode.put("경북 영덕군", "241600");
        mapAreaCode.put("경북 영양군", "241700");
        mapAreaCode.put("경북 예천군", "241800");
        mapAreaCode.put("경북 울릉군", "241900");
        mapAreaCode.put("경북 울진군", "242000");
        mapAreaCode.put("경북 의성군", "242100");
        mapAreaCode.put("경북 청도군", "242200");
        mapAreaCode.put("경북 청송군", "242300");
        mapAreaCode.put("경북 칠곡군", "242400");
        mapAreaCode.put("전남 ", "250000");
        mapAreaCode.put("전남 관내", "259999");
        mapAreaCode.put("전남 광양시", "250100");
        mapAreaCode.put("전남 나주시", "250200");
        mapAreaCode.put("전남 목포시", "250300");
        mapAreaCode.put("전남 순천시", "250400");
        mapAreaCode.put("전남 여수시", "250500");
        mapAreaCode.put("전남 강진군", "250600");
        mapAreaCode.put("전남 고흥군", "250700");
        mapAreaCode.put("전남 곡성군", "250800");
        mapAreaCode.put("전남 구례군", "250900");
        mapAreaCode.put("전남 담양군", "251000");
        mapAreaCode.put("전남 무안군", "251100");
        mapAreaCode.put("전남 보성군", "251200");
        mapAreaCode.put("전남 신안군", "251300");
        mapAreaCode.put("전남 영광군", "251400");
        mapAreaCode.put("전남 영암군", "251500");
        mapAreaCode.put("전남 완도군", "251600");
        mapAreaCode.put("전남 장성군", "251700");
        mapAreaCode.put("전남 장흥군", "251800");
        mapAreaCode.put("전남 진도군", "251900");
        mapAreaCode.put("전남 함평군", "252000");
        mapAreaCode.put("전남 해남군", "252100");
        mapAreaCode.put("전남 화순군", "252200");
        mapAreaCode.put("전북 ", "260000");
        mapAreaCode.put("전북 관내", "269999");
        mapAreaCode.put("전북 군산시", "260100");
        mapAreaCode.put("전북 김제시", "260200");
        mapAreaCode.put("전북 남원시", "260300");
        mapAreaCode.put("전북 익산시", "260400");
        mapAreaCode.put("전북 전주시", "260500");
        mapAreaCode.put("전북 전주시 덕진구", "260501");
        mapAreaCode.put("전북 전주시 완산구", "260502");
        mapAreaCode.put("전북 정읍시", "260700");
        mapAreaCode.put("전북 고창군", "260800");
        mapAreaCode.put("전북 무주군", "260900");
        mapAreaCode.put("전북 부안군", "261000");
        mapAreaCode.put("전북 순창군", "261100");
        mapAreaCode.put("전북 완주군", "261200");
        mapAreaCode.put("전북 임실군", "261300");
        mapAreaCode.put("전북 장수군", "261400");
        mapAreaCode.put("전북 진안군", "261500");
        mapAreaCode.put("제주 ", "270000");
        mapAreaCode.put("제주 관내", "279999");
        mapAreaCode.put("제주 제주시", "270100");
        mapAreaCode.put("제주 서귀포시", "270200");
        mapAreaCode.put("충남 ", "280000");
        mapAreaCode.put("충남 관내", "289999");
        mapAreaCode.put("충남 계룡시", "280100");
        mapAreaCode.put("충남 공주시", "280200");
        mapAreaCode.put("충남 논산시", "280300");
        mapAreaCode.put("충남 보령시", "280400");
        mapAreaCode.put("충남 서산시", "280500");
        mapAreaCode.put("충남 아산시", "280600");
        mapAreaCode.put("충남 천안시", "280700");
        mapAreaCode.put("충남 금산군", "280800");
        mapAreaCode.put("충남 당진시", "280900");
        mapAreaCode.put("충남 부여군", "281000");
        mapAreaCode.put("충남 서천군", "281100");
        mapAreaCode.put("충남 연기군", "281200");
        mapAreaCode.put("충남 예산군", "281300");
        mapAreaCode.put("충남 청양군", "281400");
        mapAreaCode.put("충남 태안군", "281500");
        mapAreaCode.put("충남 홍성군", "281600");
        mapAreaCode.put("충북 ", "290000");
        mapAreaCode.put("충북 관내", "299999");
        mapAreaCode.put("충북 제천시", "290100");
        mapAreaCode.put("충북 청주시", "290200");
        mapAreaCode.put("충북 청주시 상당구", "290201");
        mapAreaCode.put("충북 청주시 흥덕구", "290202");
        mapAreaCode.put("충북 청주시 청원구", "290203");
        mapAreaCode.put("충북 충주시", "290400");
        mapAreaCode.put("충북 괴산군", "290500");
        mapAreaCode.put("충북 단양군", "290600");
        mapAreaCode.put("충북 보은군", "290700");
        mapAreaCode.put("충북 영동군", "290800");
        mapAreaCode.put("충북 옥천군", "290900");
        mapAreaCode.put("충북 음성군", "291000");
        mapAreaCode.put("충북 증평군", "291100");
        mapAreaCode.put("충북 진천군", "291200");
        mapAreaCode.put("충북 청원군", "291300");
        mapAreaCode.put("전국 ", "900000");

        for(String key : mapAreaCode.keySet()){
            if(mapAreaCode.get(key).substring(2, 6).equals("0000")){
                arrayMainAreaName.add(key);
            }
        }
    }

    static public ArrayList<String> getArrayMainAreaName(){
        if(arrayMainAreaName.size() == 0){
            makeAreaCode();
        }

        return arrayMainAreaName;
    }

    static public ArrayList<String> getSubAreaName(String mainArea){
        ArrayList<String> subAreaCode = new ArrayList<>();

        for(String key : mapAreaCode.keySet()){
            if(key.contains(mainArea)){
                if(key.equals(mainArea)) {
                    subAreaCode.add(key + "지역내 전체");
                }else{
                    subAreaCode.add(key);
                }
            }
        }

        return subAreaCode;
    }

    static public ArrayList<String> getAreaCodeFromName(ArrayList<String> selectedArea){
        ArrayList<String> areaCodes = new ArrayList<>();
        for(int i = 0; i < selectedArea.size(); i++){
            String areaName = selectedArea.get(i);
            if(areaName.contains("지역내 전체")){
                areaName = areaName.substring(0, 3);
                for(String key : mapAreaCode.keySet()){
                    if(key.contains(areaName)){
                        areaCodes.add(mapAreaCode.get(key));
                    }
                }
                continue;
            }

            areaCodes.add(mapAreaCode.get(areaName));
        }

        return areaCodes;
    }
}
