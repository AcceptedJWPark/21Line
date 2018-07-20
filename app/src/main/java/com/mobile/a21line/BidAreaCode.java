package com.mobile.a21line;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kwonhong on 2018-05-10.
 */

public class BidAreaCode {
    static ArrayList<BidAreaItem> arrayAreaCode = new ArrayList<>();
    static ArrayList<BidAreaItem> arrayMainAreaName = new ArrayList<>();
    static public void makeAreaCode(){
        arrayAreaCode.add(new BidAreaItem("전국 ", "900000"));
        arrayAreaCode.add(new BidAreaItem("서울 ", "110000"));
        arrayAreaCode.add(new BidAreaItem("서울 관내", "119999"));
        arrayAreaCode.add(new BidAreaItem("서울 강남구", "110100"));
        arrayAreaCode.add(new BidAreaItem("서울 강동구", "110200"));
        arrayAreaCode.add(new BidAreaItem("서울 강북구", "110300"));
        arrayAreaCode.add(new BidAreaItem("서울 강서구", "110400"));
        arrayAreaCode.add(new BidAreaItem("서울 관악구", "110500"));
        arrayAreaCode.add(new BidAreaItem("서울 광진구", "110600"));
        arrayAreaCode.add(new BidAreaItem("서울 구로구", "110700"));
        arrayAreaCode.add(new BidAreaItem("서울 금천구", "110800"));
        arrayAreaCode.add(new BidAreaItem("서울 노원구", "110900"));
        arrayAreaCode.add(new BidAreaItem("서울 도봉구", "111000"));
        arrayAreaCode.add(new BidAreaItem("서울 동대문구", "111100"));
        arrayAreaCode.add(new BidAreaItem("서울 동작구", "111200"));
        arrayAreaCode.add(new BidAreaItem("서울 마포구", "111300"));
        arrayAreaCode.add(new BidAreaItem("서울 서대문구", "111400"));
        arrayAreaCode.add(new BidAreaItem("서울 서초구", "111500"));
        arrayAreaCode.add(new BidAreaItem("서울 성동구", "111600"));
        arrayAreaCode.add(new BidAreaItem("서울 성북구", "111700"));
        arrayAreaCode.add(new BidAreaItem("서울 송파구", "111800"));
        arrayAreaCode.add(new BidAreaItem("서울 양천구", "111900"));
        arrayAreaCode.add(new BidAreaItem("서울 영등포구", "112000"));
        arrayAreaCode.add(new BidAreaItem("서울 용산구", "112100"));
        arrayAreaCode.add(new BidAreaItem("서울 은평구", "112200"));
        arrayAreaCode.add(new BidAreaItem("서울 종로구", "112300"));
        arrayAreaCode.add(new BidAreaItem("서울 중구", "112400"));
        arrayAreaCode.add(new BidAreaItem("서울 중랑구", "112500"));
        arrayAreaCode.add(new BidAreaItem("부산 ", "120000"));
        arrayAreaCode.add(new BidAreaItem("부산 관내", "129999"));
        arrayAreaCode.add(new BidAreaItem("부산 강서구", "120100"));
        arrayAreaCode.add(new BidAreaItem("부산 금정구", "120200"));
        arrayAreaCode.add(new BidAreaItem("부산 남구", "120300"));
        arrayAreaCode.add(new BidAreaItem("부산 동구", "120400"));
        arrayAreaCode.add(new BidAreaItem("부산 동래구", "120500"));
        arrayAreaCode.add(new BidAreaItem("부산 부산진구", "120600"));
        arrayAreaCode.add(new BidAreaItem("부산 북구", "120700"));
        arrayAreaCode.add(new BidAreaItem("부산 사상구", "120800"));
        arrayAreaCode.add(new BidAreaItem("부산 사하구", "120900"));
        arrayAreaCode.add(new BidAreaItem("부산 서구", "121000"));
        arrayAreaCode.add(new BidAreaItem("부산 수영구", "121100"));
        arrayAreaCode.add(new BidAreaItem("부산 연제구", "121200"));
        arrayAreaCode.add(new BidAreaItem("부산 영도구", "121300"));
        arrayAreaCode.add(new BidAreaItem("부산 중구", "121400"));
        arrayAreaCode.add(new BidAreaItem("부산 해운대구", "121500"));
        arrayAreaCode.add(new BidAreaItem("부산 기장군", "121600"));
        arrayAreaCode.add(new BidAreaItem("대구 ", "130000"));
        arrayAreaCode.add(new BidAreaItem("대구 관내", "139999"));
        arrayAreaCode.add(new BidAreaItem("대구 남구", "130100"));
        arrayAreaCode.add(new BidAreaItem("대구 달서구", "130200"));
        arrayAreaCode.add(new BidAreaItem("대구 동구", "130300"));
        arrayAreaCode.add(new BidAreaItem("대구 북구", "130400"));
        arrayAreaCode.add(new BidAreaItem("대구 서구", "130500"));
        arrayAreaCode.add(new BidAreaItem("대구 수성구", "130600"));
        arrayAreaCode.add(new BidAreaItem("대구 중구", "130700"));
        arrayAreaCode.add(new BidAreaItem("대구 달성군", "130800"));
        arrayAreaCode.add(new BidAreaItem("인천 ", "140000"));
        arrayAreaCode.add(new BidAreaItem("인천 관내", "149999"));
        arrayAreaCode.add(new BidAreaItem("인천 계양구", "140100"));
        arrayAreaCode.add(new BidAreaItem("인천 남구", "140200"));
        arrayAreaCode.add(new BidAreaItem("인천 남동구", "140300"));
        arrayAreaCode.add(new BidAreaItem("인천 동구", "140400"));
        arrayAreaCode.add(new BidAreaItem("인천 부평구", "140500"));
        arrayAreaCode.add(new BidAreaItem("인천 서구", "140600"));
        arrayAreaCode.add(new BidAreaItem("인천 연수구", "140700"));
        arrayAreaCode.add(new BidAreaItem("인천 중구", "140800"));
        arrayAreaCode.add(new BidAreaItem("인천 강화군", "140900"));
        arrayAreaCode.add(new BidAreaItem("인천 옹진군", "141000"));
        arrayAreaCode.add(new BidAreaItem("광주 ", "150000"));
        arrayAreaCode.add(new BidAreaItem("광주 관내", "159999"));
        arrayAreaCode.add(new BidAreaItem("광주 광산구", "150100"));
        arrayAreaCode.add(new BidAreaItem("광주 남구", "150200"));
        arrayAreaCode.add(new BidAreaItem("광주 동구", "150300"));
        arrayAreaCode.add(new BidAreaItem("광주 북구", "150400"));
        arrayAreaCode.add(new BidAreaItem("광주 서구", "150500"));
        arrayAreaCode.add(new BidAreaItem("대전 ", "160000"));
        arrayAreaCode.add(new BidAreaItem("대전 관내", "169999"));
        arrayAreaCode.add(new BidAreaItem("대전 대덕구", "160100"));
        arrayAreaCode.add(new BidAreaItem("대전 동구", "160200"));
        arrayAreaCode.add(new BidAreaItem("대전 서구", "160300"));
        arrayAreaCode.add(new BidAreaItem("대전 유성구", "160400"));
        arrayAreaCode.add(new BidAreaItem("대전 중구", "160500"));
        arrayAreaCode.add(new BidAreaItem("울산 ", "170000"));
        arrayAreaCode.add(new BidAreaItem("울산 관내", "179999"));
        arrayAreaCode.add(new BidAreaItem("울산 남구", "170100"));
        arrayAreaCode.add(new BidAreaItem("울산 동구", "170200"));
        arrayAreaCode.add(new BidAreaItem("울산 북구", "170300"));
        arrayAreaCode.add(new BidAreaItem("울산 중구", "170400"));
        arrayAreaCode.add(new BidAreaItem("울산 울주군", "170500"));
        arrayAreaCode.add(new BidAreaItem("세종 ", "180000"));
        arrayAreaCode.add(new BidAreaItem("세종 관내", "189999"));
        arrayAreaCode.add(new BidAreaItem("세종 조치원읍", "180100"));
        arrayAreaCode.add(new BidAreaItem("세종 연기면", "180200"));
        arrayAreaCode.add(new BidAreaItem("세종 연동면", "180300"));
        arrayAreaCode.add(new BidAreaItem("세종 부강면", "180400"));
        arrayAreaCode.add(new BidAreaItem("세종 금남면", "180500"));
        arrayAreaCode.add(new BidAreaItem("세종 장군면", "180600"));
        arrayAreaCode.add(new BidAreaItem("세종 연서면", "180700"));
        arrayAreaCode.add(new BidAreaItem("세종 전의면", "180800"));
        arrayAreaCode.add(new BidAreaItem("세종 전동면", "180900"));
        arrayAreaCode.add(new BidAreaItem("세종 소정면", "181000"));
        arrayAreaCode.add(new BidAreaItem("세종 반곡동", "181100"));
        arrayAreaCode.add(new BidAreaItem("세종 소담동", "181200"));
        arrayAreaCode.add(new BidAreaItem("세종 보람동", "181300"));
        arrayAreaCode.add(new BidAreaItem("세종 대평동", "181400"));
        arrayAreaCode.add(new BidAreaItem("세종 가람동", "181500"));
        arrayAreaCode.add(new BidAreaItem("세종 한솔동", "181600"));
        arrayAreaCode.add(new BidAreaItem("세종 나성동", "181700"));
        arrayAreaCode.add(new BidAreaItem("세종 새롬동", "181800"));
        arrayAreaCode.add(new BidAreaItem("세종 다정동", "181900"));
        arrayAreaCode.add(new BidAreaItem("세종 어진동", "182000"));
        arrayAreaCode.add(new BidAreaItem("세종 종촌동", "182100"));
        arrayAreaCode.add(new BidAreaItem("세종 고운동", "182200"));
        arrayAreaCode.add(new BidAreaItem("세종 아름동", "182300"));
        arrayAreaCode.add(new BidAreaItem("세종 도담동", "182400"));
        arrayAreaCode.add(new BidAreaItem("강원 ", "210000"));
        arrayAreaCode.add(new BidAreaItem("강원 관내", "219999"));
        arrayAreaCode.add(new BidAreaItem("강원 강릉시", "210100"));
        arrayAreaCode.add(new BidAreaItem("강원 동해시", "210200"));
        arrayAreaCode.add(new BidAreaItem("강원 삼척시", "210300"));
        arrayAreaCode.add(new BidAreaItem("강원 속초시", "210400"));
        arrayAreaCode.add(new BidAreaItem("강원 원주시", "210500"));
        arrayAreaCode.add(new BidAreaItem("강원 춘천시", "210600"));
        arrayAreaCode.add(new BidAreaItem("강원 태백시", "210700"));
        arrayAreaCode.add(new BidAreaItem("강원 고성군", "210800"));
        arrayAreaCode.add(new BidAreaItem("강원 양구군", "210900"));
        arrayAreaCode.add(new BidAreaItem("강원 양양군", "211000"));
        arrayAreaCode.add(new BidAreaItem("강원 영월군", "211100"));
        arrayAreaCode.add(new BidAreaItem("강원 인제군", "211200"));
        arrayAreaCode.add(new BidAreaItem("강원 정선군", "211300"));
        arrayAreaCode.add(new BidAreaItem("강원 철원군", "211400"));
        arrayAreaCode.add(new BidAreaItem("강원 평창군", "211500"));
        arrayAreaCode.add(new BidAreaItem("강원 홍천군", "211600"));
        arrayAreaCode.add(new BidAreaItem("강원 화천군", "211700"));
        arrayAreaCode.add(new BidAreaItem("강원 횡성군", "211800"));
        arrayAreaCode.add(new BidAreaItem("경기 ", "220000"));
        arrayAreaCode.add(new BidAreaItem("경기 관내", "229999"));
        arrayAreaCode.add(new BidAreaItem("경기 고양시", "220100"));
        arrayAreaCode.add(new BidAreaItem("경기 고양시 덕양구", "220101"));
        arrayAreaCode.add(new BidAreaItem("경기 고양시 일산동구", "220102"));
        arrayAreaCode.add(new BidAreaItem("경기 고양시 일산서구", "220103"));
        arrayAreaCode.add(new BidAreaItem("경기 과천시", "220400"));
        arrayAreaCode.add(new BidAreaItem("경기 광명시", "220500"));
        arrayAreaCode.add(new BidAreaItem("경기 광주시", "220600"));
        arrayAreaCode.add(new BidAreaItem("경기 구리시", "220700"));
        arrayAreaCode.add(new BidAreaItem("경기 군포시", "220800"));
        arrayAreaCode.add(new BidAreaItem("경기 김포시", "220900"));
        arrayAreaCode.add(new BidAreaItem("경기 남양주시", "221000"));
        arrayAreaCode.add(new BidAreaItem("경기 동두천시", "221100"));
        arrayAreaCode.add(new BidAreaItem("경기 부천시", "221200"));
        arrayAreaCode.add(new BidAreaItem("경기 부천시 소사구", "221201"));
        arrayAreaCode.add(new BidAreaItem("경기 부천시 오정구", "221202"));
        arrayAreaCode.add(new BidAreaItem("경기 부천시 원미구", "221203"));
        arrayAreaCode.add(new BidAreaItem("경기 성남시", "221500"));
        arrayAreaCode.add(new BidAreaItem("경기 성남시 분당구", "221501"));
        arrayAreaCode.add(new BidAreaItem("경기 성남시 수정구", "221502"));
        arrayAreaCode.add(new BidAreaItem("경기 성남시 중원구", "221503"));
        arrayAreaCode.add(new BidAreaItem("경기 수원시", "221800"));
        arrayAreaCode.add(new BidAreaItem("경기 수원시 권선구", "221801"));
        arrayAreaCode.add(new BidAreaItem("경기 수원시 영통구", "221802"));
        arrayAreaCode.add(new BidAreaItem("경기 수원시 장안구", "221803"));
        arrayAreaCode.add(new BidAreaItem("경기 수원시 팔달구", "221804"));
        arrayAreaCode.add(new BidAreaItem("경기 시흥시", "222200"));
        arrayAreaCode.add(new BidAreaItem("경기 안산시", "222300"));
        arrayAreaCode.add(new BidAreaItem("경기 안산시 단원구", "222301"));
        arrayAreaCode.add(new BidAreaItem("경기 안산시 상록구", "222302"));
        arrayAreaCode.add(new BidAreaItem("경기 안성시", "222500"));
        arrayAreaCode.add(new BidAreaItem("경기 안양시", "222600"));
        arrayAreaCode.add(new BidAreaItem("경기 안양시 동안구", "222601"));
        arrayAreaCode.add(new BidAreaItem("경기 안양시 만안구", "222602"));
        arrayAreaCode.add(new BidAreaItem("경기 양주시", "222800"));
        arrayAreaCode.add(new BidAreaItem("경기 오산시", "222900"));
        arrayAreaCode.add(new BidAreaItem("경기 용인시", "223000"));
        arrayAreaCode.add(new BidAreaItem("경기 용인시 기흥구", "223001"));
        arrayAreaCode.add(new BidAreaItem("경기 용인시 수지구", "223002"));
        arrayAreaCode.add(new BidAreaItem("경기 용인시 처인구", "223003"));
        arrayAreaCode.add(new BidAreaItem("경기 의왕시", "223300"));
        arrayAreaCode.add(new BidAreaItem("경기 의정부시", "223400"));
        arrayAreaCode.add(new BidAreaItem("경기 이천시", "223500"));
        arrayAreaCode.add(new BidAreaItem("경기 파주시", "223600"));
        arrayAreaCode.add(new BidAreaItem("경기 평택시", "223700"));
        arrayAreaCode.add(new BidAreaItem("경기 포천시", "223800"));
        arrayAreaCode.add(new BidAreaItem("경기 하남시", "223900"));
        arrayAreaCode.add(new BidAreaItem("경기 화성시", "224000"));
        arrayAreaCode.add(new BidAreaItem("경기 가평군", "224100"));
        arrayAreaCode.add(new BidAreaItem("경기 양평군", "224200"));
        arrayAreaCode.add(new BidAreaItem("경기 여주군", "224300"));
        arrayAreaCode.add(new BidAreaItem("경기 연천군", "224400"));
        arrayAreaCode.add(new BidAreaItem("경남 ", "230000"));
        arrayAreaCode.add(new BidAreaItem("경남 관내", "239999"));
        arrayAreaCode.add(new BidAreaItem("경남 거제시", "230100"));
        arrayAreaCode.add(new BidAreaItem("경남 김해시", "230200"));
        arrayAreaCode.add(new BidAreaItem("경남 마산시", "230300"));
        arrayAreaCode.add(new BidAreaItem("경남 밀양시", "230400"));
        arrayAreaCode.add(new BidAreaItem("경남 사천시", "230500"));
        arrayAreaCode.add(new BidAreaItem("경남 양산시", "230600"));
        arrayAreaCode.add(new BidAreaItem("경남 진주시", "230700"));
        arrayAreaCode.add(new BidAreaItem("경남 진해시", "230800"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시", "230900"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시 마산합포구", "230901"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시 마산회원구", "230902"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시 의창구", "230903"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시 성산구", "230904"));
        arrayAreaCode.add(new BidAreaItem("경남 창원시 진해구", "230905"));
        arrayAreaCode.add(new BidAreaItem("경남 통영시", "231000"));
        arrayAreaCode.add(new BidAreaItem("경남 거창군", "231100"));
        arrayAreaCode.add(new BidAreaItem("경남 고성군", "231200"));
        arrayAreaCode.add(new BidAreaItem("경남 남해군", "231300"));
        arrayAreaCode.add(new BidAreaItem("경남 산청군", "231400"));
        arrayAreaCode.add(new BidAreaItem("경남 의령군", "231500"));
        arrayAreaCode.add(new BidAreaItem("경남 창녕군", "231600"));
        arrayAreaCode.add(new BidAreaItem("경남 하동군", "231700"));
        arrayAreaCode.add(new BidAreaItem("경남 함안군", "231800"));
        arrayAreaCode.add(new BidAreaItem("경남 함양군", "231900"));
        arrayAreaCode.add(new BidAreaItem("경남 합천군", "232000"));
        arrayAreaCode.add(new BidAreaItem("경북 ", "240000"));
        arrayAreaCode.add(new BidAreaItem("경북 관내", "249999"));
        arrayAreaCode.add(new BidAreaItem("경북 경산시", "240100"));
        arrayAreaCode.add(new BidAreaItem("경북 경주시", "240200"));
        arrayAreaCode.add(new BidAreaItem("경북 구미시", "240300"));
        arrayAreaCode.add(new BidAreaItem("경북 김천시", "240400"));
        arrayAreaCode.add(new BidAreaItem("경북 문경시", "240500"));
        arrayAreaCode.add(new BidAreaItem("경북 상주시", "240600"));
        arrayAreaCode.add(new BidAreaItem("경북 안동시", "240700"));
        arrayAreaCode.add(new BidAreaItem("경북 영주시", "240800"));
        arrayAreaCode.add(new BidAreaItem("경북 영천시", "240900"));
        arrayAreaCode.add(new BidAreaItem("경북 포항시", "241000"));
        arrayAreaCode.add(new BidAreaItem("경북 포항시 남구", "241001"));
        arrayAreaCode.add(new BidAreaItem("경북 포항시 북구", "241002"));
        arrayAreaCode.add(new BidAreaItem("경북 고령군", "241200"));
        arrayAreaCode.add(new BidAreaItem("경북 군위군", "241300"));
        arrayAreaCode.add(new BidAreaItem("경북 봉화군", "241400"));
        arrayAreaCode.add(new BidAreaItem("경북 성주군", "241500"));
        arrayAreaCode.add(new BidAreaItem("경북 영덕군", "241600"));
        arrayAreaCode.add(new BidAreaItem("경북 영양군", "241700"));
        arrayAreaCode.add(new BidAreaItem("경북 예천군", "241800"));
        arrayAreaCode.add(new BidAreaItem("경북 울릉군", "241900"));
        arrayAreaCode.add(new BidAreaItem("경북 울진군", "242000"));
        arrayAreaCode.add(new BidAreaItem("경북 의성군", "242100"));
        arrayAreaCode.add(new BidAreaItem("경북 청도군", "242200"));
        arrayAreaCode.add(new BidAreaItem("경북 청송군", "242300"));
        arrayAreaCode.add(new BidAreaItem("경북 칠곡군", "242400"));
        arrayAreaCode.add(new BidAreaItem("전남 ", "250000"));
        arrayAreaCode.add(new BidAreaItem("전남 관내", "259999"));
        arrayAreaCode.add(new BidAreaItem("전남 광양시", "250100"));
        arrayAreaCode.add(new BidAreaItem("전남 나주시", "250200"));
        arrayAreaCode.add(new BidAreaItem("전남 목포시", "250300"));
        arrayAreaCode.add(new BidAreaItem("전남 순천시", "250400"));
        arrayAreaCode.add(new BidAreaItem("전남 여수시", "250500"));
        arrayAreaCode.add(new BidAreaItem("전남 강진군", "250600"));
        arrayAreaCode.add(new BidAreaItem("전남 고흥군", "250700"));
        arrayAreaCode.add(new BidAreaItem("전남 곡성군", "250800"));
        arrayAreaCode.add(new BidAreaItem("전남 구례군", "250900"));
        arrayAreaCode.add(new BidAreaItem("전남 담양군", "251000"));
        arrayAreaCode.add(new BidAreaItem("전남 무안군", "251100"));
        arrayAreaCode.add(new BidAreaItem("전남 보성군", "251200"));
        arrayAreaCode.add(new BidAreaItem("전남 신안군", "251300"));
        arrayAreaCode.add(new BidAreaItem("전남 영광군", "251400"));
        arrayAreaCode.add(new BidAreaItem("전남 영암군", "251500"));
        arrayAreaCode.add(new BidAreaItem("전남 완도군", "251600"));
        arrayAreaCode.add(new BidAreaItem("전남 장성군", "251700"));
        arrayAreaCode.add(new BidAreaItem("전남 장흥군", "251800"));
        arrayAreaCode.add(new BidAreaItem("전남 진도군", "251900"));
        arrayAreaCode.add(new BidAreaItem("전남 함평군", "252000"));
        arrayAreaCode.add(new BidAreaItem("전남 해남군", "252100"));
        arrayAreaCode.add(new BidAreaItem("전남 화순군", "252200"));
        arrayAreaCode.add(new BidAreaItem("전북 ", "260000"));
        arrayAreaCode.add(new BidAreaItem("전북 관내", "269999"));
        arrayAreaCode.add(new BidAreaItem("전북 군산시", "260100"));
        arrayAreaCode.add(new BidAreaItem("전북 김제시", "260200"));
        arrayAreaCode.add(new BidAreaItem("전북 남원시", "260300"));
        arrayAreaCode.add(new BidAreaItem("전북 익산시", "260400"));
        arrayAreaCode.add(new BidAreaItem("전북 전주시", "260500"));
        arrayAreaCode.add(new BidAreaItem("전북 전주시 덕진구", "260501"));
        arrayAreaCode.add(new BidAreaItem("전북 전주시 완산구", "260502"));
        arrayAreaCode.add(new BidAreaItem("전북 정읍시", "260700"));
        arrayAreaCode.add(new BidAreaItem("전북 고창군", "260800"));
        arrayAreaCode.add(new BidAreaItem("전북 무주군", "260900"));
        arrayAreaCode.add(new BidAreaItem("전북 부안군", "261000"));
        arrayAreaCode.add(new BidAreaItem("전북 순창군", "261100"));
        arrayAreaCode.add(new BidAreaItem("전북 완주군", "261200"));
        arrayAreaCode.add(new BidAreaItem("전북 임실군", "261300"));
        arrayAreaCode.add(new BidAreaItem("전북 장수군", "261400"));
        arrayAreaCode.add(new BidAreaItem("전북 진안군", "261500"));
        arrayAreaCode.add(new BidAreaItem("제주 ", "270000"));
        arrayAreaCode.add(new BidAreaItem("제주 관내", "279999"));
        arrayAreaCode.add(new BidAreaItem("제주 제주시", "270100"));
        arrayAreaCode.add(new BidAreaItem("제주 서귀포시", "270200"));
        arrayAreaCode.add(new BidAreaItem("충남 ", "280000"));
        arrayAreaCode.add(new BidAreaItem("충남 관내", "289999"));
        arrayAreaCode.add(new BidAreaItem("충남 계룡시", "280100"));
        arrayAreaCode.add(new BidAreaItem("충남 공주시", "280200"));
        arrayAreaCode.add(new BidAreaItem("충남 논산시", "280300"));
        arrayAreaCode.add(new BidAreaItem("충남 보령시", "280400"));
        arrayAreaCode.add(new BidAreaItem("충남 서산시", "280500"));
        arrayAreaCode.add(new BidAreaItem("충남 아산시", "280600"));
        arrayAreaCode.add(new BidAreaItem("충남 천안시", "280700"));
        arrayAreaCode.add(new BidAreaItem("충남 금산군", "280800"));
        arrayAreaCode.add(new BidAreaItem("충남 당진시", "280900"));
        arrayAreaCode.add(new BidAreaItem("충남 부여군", "281000"));
        arrayAreaCode.add(new BidAreaItem("충남 서천군", "281100"));
        arrayAreaCode.add(new BidAreaItem("충남 연기군", "281200"));
        arrayAreaCode.add(new BidAreaItem("충남 예산군", "281300"));
        arrayAreaCode.add(new BidAreaItem("충남 청양군", "281400"));
        arrayAreaCode.add(new BidAreaItem("충남 태안군", "281500"));
        arrayAreaCode.add(new BidAreaItem("충남 홍성군", "281600"));
        arrayAreaCode.add(new BidAreaItem("충북 ", "290000"));
        arrayAreaCode.add(new BidAreaItem("충북 관내", "299999"));
        arrayAreaCode.add(new BidAreaItem("충북 제천시", "290100"));
        arrayAreaCode.add(new BidAreaItem("충북 청주시", "290200"));
        arrayAreaCode.add(new BidAreaItem("충북 청주시 상당구", "290201"));
        arrayAreaCode.add(new BidAreaItem("충북 청주시 흥덕구", "290202"));
        arrayAreaCode.add(new BidAreaItem("충북 청주시 청원구", "290203"));
        arrayAreaCode.add(new BidAreaItem("충북 충주시", "290400"));
        arrayAreaCode.add(new BidAreaItem("충북 괴산군", "290500"));
        arrayAreaCode.add(new BidAreaItem("충북 단양군", "290600"));
        arrayAreaCode.add(new BidAreaItem("충북 보은군", "290700"));
        arrayAreaCode.add(new BidAreaItem("충북 영동군", "290800"));
        arrayAreaCode.add(new BidAreaItem("충북 옥천군", "290900"));
        arrayAreaCode.add(new BidAreaItem("충북 음성군", "291000"));
        arrayAreaCode.add(new BidAreaItem("충북 증평군", "291100"));
        arrayAreaCode.add(new BidAreaItem("충북 진천군", "291200"));
        arrayAreaCode.add(new BidAreaItem("충북 청원군", "291300"));

        for(int i = 0; i < arrayAreaCode.size(); i++){
            BidAreaItem item = arrayAreaCode.get(i);
            if(item.getCode().substring(2, 6).equals("0000")){
                arrayMainAreaName.add(item);
            }
        }
    }

    static public ArrayList<BidAreaItem> getArrayMainAreaName(){
        if(arrayMainAreaName.size() == 0){
            makeAreaCode();
        }

        return arrayMainAreaName;
    }

    static public ArrayList<BidAreaItem> getSubAreaName(String mainArea){
        ArrayList<BidAreaItem> subAreaCode = new ArrayList<>();

        for(int i = 0; i < arrayAreaCode.size(); i++){
            BidAreaItem item = arrayAreaCode.get(i);
            if(item.getName().contains(mainArea)){
                subAreaCode.add(item);
            }
        }

        return subAreaCode;
    }

    static public class BidAreaItem implements Serializable {
        private String name;
        private String code;
        private boolean isSelected;

        public BidAreaItem(String name, String code){
            this.code = code;
            this.name = name;
            this.isSelected = true;
        }

        public String getName(){
            return this.name;
        }

        public String getCode(){
            return this.code;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean isSelected){
            this.isSelected = isSelected;
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof  BidAreaItem){
                BidAreaItem item = (BidAreaItem)obj;
                if(this.getName().equals(item.getName()) && this.getCode().equals(item.getCode()))
                    return true;
            }

            return false;
        }
    }
}
