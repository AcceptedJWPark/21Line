package com.mobile.a21line;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kwonhong on 2018-05-10.
 */

public class BidUpCode {
    static ArrayList<String> arrayCode = new ArrayList();
    static ArrayList<BidUpCodeItem> arrayConsBidCodeParent = new ArrayList<>();
    static HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> mapConsBidCodeChild = new HashMap<>();
    static ArrayList<BidUpCodeItem> arrayPurcBidCodeParent = new ArrayList<>();
    static HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> mapPurcBidCodeChild = new HashMap<>();
    static ArrayList<BidUpCodeItem> arrayServBidCodeParent = new ArrayList<>();
    static HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> mapServBidCodeChild = new HashMap<>();
    static ArrayList<BidUpCodeItem> arraySellBidCodeParent = new ArrayList<>();
    static HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> mapSellBidCodeChild = new HashMap<>();

    static public void makeArrayCode(){
        arrayCode.add("일반건설공사_110_000_ _");
        arrayCode.add("토목공사_110_100_ _토목");
        arrayCode.add("토목건축공사_110_101_ _토건");
        arrayCode.add("건축공사_110_102_ _건축");
        arrayCode.add("조경공사_110_110_ _조경");
        arrayCode.add("산업.환경설비공사_110_200_ _산업/환경");
        arrayCode.add("전문건설공사_120_000_ _");
        arrayCode.add("실내건축_120_100_ _실내건축");
        arrayCode.add("토공공사_120_110_ _토공");
        arrayCode.add("미장방수조적_120_120_ _미장/방수");
        arrayCode.add("석공공사_120_130_ _석공");
        arrayCode.add("도장공사_120_140_ _도장");
        arrayCode.add("비계구조물해체_120_150_ _비계구조물");
        arrayCode.add("석면해체_120_151_ _석면해체");
        arrayCode.add("금속구조물창호_120_160_ _금속구조물");
        arrayCode.add("지붕판금건축물조립_120_170_ _지붕,판금,건축물조립");
        arrayCode.add("철근콘크리트공사_120_180_ _철콘");
        arrayCode.add("상하수도공사_120_190_ _상하수도");
        arrayCode.add("보링그라우팅공사_120_200_ _보링그라우팅");
        arrayCode.add("철도궤도공사_120_210_ _철도궤도");
        arrayCode.add("포장공사_120_220_ _포장");
        arrayCode.add("수중공사_120_230_ _수중");
        arrayCode.add("조경식재공사_120_240_ _조경식재");
        arrayCode.add("조경시설물설치공사_120_250_ _조경시설물");
        arrayCode.add("강구조물공사_120_260_ _강구조물");
        arrayCode.add("승강기설치_120_270_ _승강기");
        arrayCode.add("철강재설치공사_120_290_ _철강재");
        arrayCode.add("삭도설치.준설공사_120_300_ _삭도/준설");
        arrayCode.add("시설물유지관리_120_310_ _시설물");
        arrayCode.add("기계설비공사업_120_320_ _기계설비");
        arrayCode.add("지하수개발업_120_330_ _지하수개발");
        arrayCode.add("난방공사_120_340_ _난방");
        arrayCode.add("가스시설공사_120_341_ _가스");
        arrayCode.add("특정열사용기자재시공업_120_350_ _특장열");
        arrayCode.add("정비사업(재건축,재개발,재정비등)_120_400_ _정비산업");
        arrayCode.add("문화재관련공사_120_500_ _문화재");
        arrayCode.add("자연휴양림_120_600_ _휴양림");
        arrayCode.add("광해방지사업(토양개량.복원.정화)_120_700_ _광해방지사업");
        arrayCode.add("전기/통신/산림/환경/기타공사_199_000_ _");
        arrayCode.add("전기공사_199_100_ _전기");
        arrayCode.add("정보통신공사_199_110_ _정보통신");
        arrayCode.add("일반소방시설(기계)_199_120_ _일반소방시설(기계)");
        arrayCode.add("일반소방시설(전기)_199_121_ _일반소방시설(전기)");
        arrayCode.add("전문소방시설공사_199_122_ _전문소방시설");
        arrayCode.add("대기/수질방지시설업(소음.진동방지)_199_130_ _수질/소음/진동");
        arrayCode.add("신재생에너지설비_199_140_ _신재생에너지");
        arrayCode.add("오수처리시설등설계.시공업_199_160_ _오수처리");
        arrayCode.add("산림사업(임도설치/산림복구)_199_190_ _산림산업");
        arrayCode.add("골재채취업(육상골재)_199_200_ _골재채취업");
        arrayCode.add("강선건조업,선박수리_199_220_ _강선/선박");
        arrayCode.add("기타공사_199_990_ _기타");
        arrayCode.add("엔지니어링_210_000_ _");
        arrayCode.add("산업설비,구조 ENG._210_100_ _산업/구조ENG");
        arrayCode.add("전자.정보통신 ENG._210_110_ _전자정보ENG");
        arrayCode.add("건설 ENG._210_130_ _건설ENG");
        arrayCode.add("전기 ENG._210_140_ _전기ENG");
        arrayCode.add("환경 ENG._210_160_ _환경ENG");
        arrayCode.add("기타 ENG._210_999_ _기타ENG");
        arrayCode.add("감리관련_211_000_ _");
        arrayCode.add("종합감리용역_211_100_ _종합감리");
        arrayCode.add("토목감리용역_211_110_ _토목감리");
        arrayCode.add("건축감리용역_211_120_ _건축감리");
        arrayCode.add("기계설비감리용역_211_130_ _기계감리");
        arrayCode.add("전력감리업_211_140_ _전력감리");
        arrayCode.add("일반소방공사감리_211_150_ _일반소방감리");
        arrayCode.add("전문소방공사감리_211_160_ _전문소방감리");
        arrayCode.add("정보시스템감리_211_170_ _정보시스템감리");
        arrayCode.add("기타감리업_211_999_ _기타감리");
        arrayCode.add("설계관련_212_000_ _");
        arrayCode.add("건축관련설계_212_100_ _건축설계");
        arrayCode.add("설계업(전력)_212_110_ _전력설계");
        arrayCode.add("전문소방시설설계_212_120_ _전문소방");
        arrayCode.add("일반소방설계(기계)_212_130_ _일반소방(기계)");
        arrayCode.add("일반소방설계(전기)_212_140_ _일반소방(전기)");
        arrayCode.add("종합설계용역_212_150_ _종합설계");
        arrayCode.add("안전진단_213_000_ _");
        arrayCode.add("안전진단(교량및터널/항만/건축)_213_100_ _안전진단(교량/터널/항만/건축)");
        arrayCode.add("안전진단(수리시설/전기/무대시설)_213_130_ _안전진단(수리/전기/무대)");
        arrayCode.add("광고/홍보/간판_214_000_ _");
        arrayCode.add("옥외광고업(안내.표지판.간판등)_214_100_ _옥외광고");
        arrayCode.add("영상(홍보)물,동영상제작_214_120_ _영상물");
        arrayCode.add("광고.홍보.디자인.광고대행_214_999_ _광고관련기타");
        arrayCode.add("위탁(식당/어린이집/자판기/검침)등_215_000_ _");
        arrayCode.add("위탁용역(어린이집.교육시설/복지후생등)_215_120_ _위탁");
        arrayCode.add("식당위탁용역_215_130_ _식당위탁");
        arrayCode.add("자판기관리_215_150_ _자판기관리");
        arrayCode.add("기타관리위탁(검침.기타유지운영관리등)_215_999_ _기타/파견");
        arrayCode.add("위생(청소/소독/물탱크/세탁/병원폐기물)_216_000_ _");
        arrayCode.add("소독_216_120_ _소독");
        arrayCode.add("세탁물처리,병원폐기물처리_216_130_ _세탁물/병원폐기물");
        arrayCode.add("위생관리용역업_216_140_ _위생관리용역업");
        arrayCode.add("물탱크청소,기타위생청소_216_999_ _기타위생");
        arrayCode.add("관리업_217_000_ _");
        arrayCode.add("안전관련관리업/항공선박관리.정비업_217_100_ _안전관리/항공/선박");
        arrayCode.add("소방시설관리업_217_110_ _소방관리");
        arrayCode.add("주택.건물/아파트관리_217_120_ _건물관리");
        arrayCode.add("시설물유지관리업_217_130_ _시설물유지관리");
        arrayCode.add("자동차관리.정비업.세차_217_140_ _자동차.정비(관리)");
        arrayCode.add("주차장관리_217_150_ _주차장관리");
        arrayCode.add("조림/영림/수목 관리_217_160_ _조경관리");
        arrayCode.add("승강기유지보수정비_217_170_ _승강기유지");
        arrayCode.add("기타유지보수정비_217_180_ _기타유지보수");
        arrayCode.add("신재생에너지_218_000_ _");
        arrayCode.add("신재생에너지전문기업_218_100_ _신재생에너지");
        arrayCode.add("에너지절약전문기업(esco)_218_110_ _esco");
        arrayCode.add("측량/사진_219_000_ _");
        arrayCode.add("측지측량업_219_100_ _측량");
        arrayCode.add("공공측량업_219_110_ _공공측량");
        arrayCode.add("항공사진관련업_219_120_ _항공");
        arrayCode.add("지도관련,해도제작_219_130_ _지도");
        arrayCode.add("지하시설물측량업_219_140_ _지하시설물측량");
        arrayCode.add("수로사업_219_150_ _수로사업");
        arrayCode.add("연구.학술.경영.컨설팅.특허/리서치.조사_220_000_ _");
        arrayCode.add("학술.연구.경영.컨설팅.특허_220_100_ _연구/학술");
        arrayCode.add("원가계산용역_220_110_ _원가계산");
        arrayCode.add("리서치/조사/모집_220_120_ _리서치");
        arrayCode.add("폐기물_221_000_ _");
        arrayCode.add("폐기물수집.운반_221_100_ _폐기물수집/운반");
        arrayCode.add("폐기물처리업_221_110_ _폐기물처리");
        arrayCode.add("폐기물종합처리업_221_120_ _폐기물종합처리");
        arrayCode.add("폐기물해양수거업_221_130_ _폐기물해양수거");
        arrayCode.add("컴퓨터통신_222_000_ _");
        arrayCode.add("소프트웨어사업자/SI_222_110_ _소프트웨어사업자/SI");
        arrayCode.add("컴퓨터및주변기기유지보수_222_120_ _컴퓨터유지보수");
        arrayCode.add("기타정보통신관련_222_999_ _기타정보통신");
        arrayCode.add("문화재관련(수리/지표조사)_223_000_ _");
        arrayCode.add("문화재수리업_223_100_ _문화재수리");
        arrayCode.add("문화재지표조사_223_110_ _문화지표");
        arrayCode.add("보험_224_000_ _");
        arrayCode.add("보험._224_100_ _보험");
        arrayCode.add("리스/임대/대여_225_000_ _");
        arrayCode.add("리스.대여.임대_225_100_ _리스/임대");
        arrayCode.add("기계대여업_225_110_ _기계대여");
        arrayCode.add("은행/의료/장의(무연고.행려등)_226_000_ _");
        arrayCode.add("은행관련_226_100_ _은행");
        arrayCode.add("의료관련_226_110_ _의료");
        arrayCode.add("장의관련(분묘이장.무연고.행려등)_226_120_ _장의/행려");
        arrayCode.add("기타 서비스_226_999_ _기타");
        arrayCode.add("오수/분뇨/폐수_227_000_ _");
        arrayCode.add("오수.분뇨.폐수용역_227_100_ _오수/폐수");
        arrayCode.add("수로조사_228_000_ _");
        arrayCode.add("수로조사업_228_100_ _수로조사");
        arrayCode.add("운송/해운/여행_229_000_ _");
        arrayCode.add("화물,해운,특수운송_229_100_ _화물,해운,특수");
        arrayCode.add("수학.국내여행,해외여행_229_120_ _국내외여행");
        arrayCode.add("숙박업,택배업_229_140_ _숙박/택배");
        arrayCode.add("여객자동차운송(전세/출퇴근버스)_229_999_ _여객자동차운송");
        arrayCode.add("기술사사무소_230_000_ _");
        arrayCode.add("기술사사무소._230_100_ _기술사사무소");
        arrayCode.add("민간투자_231_000_ _");
        arrayCode.add("민간투자사업(BTL/SOC)_231_100_ _민간투자");
        arrayCode.add("어장정화_232_000_ _");
        arrayCode.add("어장정화.정비업_232_100_ _어장정화");
        arrayCode.add("환경평가_233_000_ _");
        arrayCode.add("환경평가/인구영향평가/지하수영향평가_233_100_ _환경/인구/지하수(평가)");
        arrayCode.add("대기대행업_233_110_ _대기/환경(대행)");
        arrayCode.add("개인정보영향평가기관_233_120_ _영향평가기관");
        arrayCode.add("전문자격(세무.회계.법무)_234_000_ _");
        arrayCode.add("세무사/회계사/법무사전문자격_234_100_ _세무/회계/법무");
        arrayCode.add("전시/행사/이벤트/부스_235_000_ _");
        arrayCode.add("전시.행사.이벤트.부스_235_100_ _전시/행사/이벤트");
        arrayCode.add("산업디자인(조각,조형물포함)_236_000_ _");
        arrayCode.add("산업디자인용역(조각,조형물포함)_236_100_ _디자인");
        arrayCode.add("비파괴검사_237_000_ _");
        arrayCode.add("비파괴검사업_237_100_ _비파괴");
        arrayCode.add("알뜰시장_238_000_ _");
        arrayCode.add("알뜰시장._238_100_ _알뜰시장");
        arrayCode.add("재활용_239_000_ _");
        arrayCode.add("재활용._239_100_ _재활용");
        arrayCode.add("파견/대행/번역/교육/ISO_240_000_ _");
        arrayCode.add("근로자파견/업무대행_240_100_ _근로자파견/업무대행");
        arrayCode.add("번역/교육/ISO인증_240_200_ _번역/교육/ISO인증");
        arrayCode.add("보안,경비,무인경비_241_000_ _");
        arrayCode.add("보안.경비.무인/기계경비_241_100_ _보안/기계경비");
        arrayCode.add("전기안전/CCTV조사_242_000_ _");
        arrayCode.add("전기안전관리업._242_100_ _전기안전관리업");
        arrayCode.add("CCTV조사_242_200_ _CCTV조사");
        arrayCode.add("골재채취업_243_000_ _");
        arrayCode.add("골재채취업._243_100_ _골재채취");
        arrayCode.add("기상장비업_199_210_ _기상장비");
        arrayCode.add("석면조사_244_000_ _");
        arrayCode.add("석면조사업_244_100_ _석면조사");
        arrayCode.add("기타용역_299_000_ _");
        arrayCode.add("기타용역._299_999_ _기타");
        arrayCode.add("가구.사무용가구.교구/주방관련_310_000_ _");
        arrayCode.add("가구관련제품_310_100_ _가구");
        arrayCode.add("주방관련제품_310_110_ _주방");
        arrayCode.add("건설/시설자재_311_000_ _");
        arrayCode.add("건설자재_311_100_ _건자재");
        arrayCode.add("시설자재_311_200_ _시설자재");
        arrayCode.add("조경관련_312_000_ _");
        arrayCode.add("농약.비료.약재류_312_100_ _농약/비료");
        arrayCode.add("상토.조경석/조경자재_312_110_ _상토/조경석");
        arrayCode.add("종자.묘목.잔디류,화훼,수목_312_120_ _종자/묘목/잔디/화훼");
        arrayCode.add("기계장비/산업기계/농축산기계_313_000_ _");
        arrayCode.add("중장비,건설기계_313_100_ _건설장비");
        arrayCode.add("농축산기계_313_110_ _농.축기계");
        arrayCode.add("작업공구_313_120_ _작업공구");
        arrayCode.add("기계장비.유체조절기기_313_130_ _기계장비/유체기기");
        arrayCode.add("산업기계_313_140_ _산업기계");
        arrayCode.add("산업용냉동.냉장기기_313_150_ _냉동/냉장기기");
        arrayCode.add("전기(UPS,자동제어)/조명_314_000_ _");
        arrayCode.add("전기,전선,전기,무정전,UPS,자동제어관련자재_314_100_ _전기/전선");
        arrayCode.add("조명_314_110_ _조명");
        arrayCode.add("배전반,변압기_314_120_ _배전/변압");
        arrayCode.add("전동기/회전기기/축전지_316_000_ _");
        arrayCode.add("엔진.전동기_316_100_ _엔진/전동");
        arrayCode.add("전지.발전기.동력전달.축전지_316_110_ _전지/발전기");
        arrayCode.add("모터,펌프_316_120_ _모터/펌프");
        arrayCode.add("기타 회전기기.경전기_316_999_ _기타회전기기/경전기");
        arrayCode.add("전자.통신.방송기기.신호등.소방안전.탐지(CCTV)_317_000_ _");
        arrayCode.add("신호등관련_317_100_ _신호등");
        arrayCode.add("보안.감시.탐지장비(cctv)_317_110_ _보안/감시");
        arrayCode.add("전자.통신.방송기기_317_120_ _전자/방송기기");
        arrayCode.add("소방,안전관련(소화기등)_317_130_ _소방");
        arrayCode.add("군수품관련_318_000_ _");
        arrayCode.add("군수품_318_100_ _군수품");
        arrayCode.add("식음료/농.축.해산물(급식,부식)/건강보조_320_000_ _");
        arrayCode.add("건강보조.약용식품_320_110_ _건강보조");
        arrayCode.add("급식.부식(농축해산물)_320_120_ _급식/부식/농축해산");
        arrayCode.add("식음료_320_130_ _식음료");
        arrayCode.add("운송기기/하역기계_321_000_ _");
        arrayCode.add("운송기기관련_321_100_ _운송기기");
        arrayCode.add("전동차,철도관련_321_110_ _전동차");
        arrayCode.add("항공,해양조선관련_321_120_ _항공/해양조선");
        arrayCode.add("하역기계.장비,승강기,콘베이어_321_130_ _하역/승강/콘베어");
        arrayCode.add("의료기기/의약품_325_000_ _");
        arrayCode.add("의료기기_325_100_ _의료기");
        arrayCode.add("의약품_325_110_ _의약품");
        arrayCode.add("기타약품관련_325_120_ _기타약품");
        arrayCode.add("운동/레저/악기_326_000_ _");
        arrayCode.add("운동관련제품_326_100_ _운동");
        arrayCode.add("레저관련제품_326_110_ _레저");
        arrayCode.add("악기,장난감,교육관련교구,장식품_326_200_ _악기/장난감/교육관련");
        arrayCode.add("의류/섬유_327_000_ _");
        arrayCode.add("의류,가방_327_100_ _의류/가방");
        arrayCode.add("섬유관련(수건,타올포함)_327_200_ _섬유");
        arrayCode.add("연료관련/원자재_329_000_ _");
        arrayCode.add("연료_329_100_ _연료");
        arrayCode.add("가스연료첨가제,윤활제.그리스및방부_329_110_ _첨가제/윤활제");
        arrayCode.add("화학제품_329_200_ _화학제품");
        arrayCode.add("원자재_329_300_ _원자재");
        arrayCode.add("수처리.오염.환경/청소장비_331_000_ _");
        arrayCode.add("수처리오염방지환경구매_331_100_ _수처리");
        arrayCode.add("청소장비.소모품_331_110_ _청소용품");
        arrayCode.add("플라스틱/합성수지_332_000_ _");
        arrayCode.add("고무및플라스틱_332_100_ _고무/플라스틱");
        arrayCode.add("합성수지_332_110_ _합성수지");
        arrayCode.add("실물모형_332_120_ _실물모형");
        arrayCode.add("기타 플라스틱및고무_332_130_ _기타플라스틱고무");
        arrayCode.add("영상장비,사진/졸업앨범_333_000_ _");
        arrayCode.add("사진,영상장비_333_100_ _사진");
        arrayCode.add("졸업앨범_333_110_ _앨범");
        arrayCode.add("도서출판인쇄(안내.표지판)_334_000_ _");
        arrayCode.add("인쇄,출판,판촉물(안내.표지판)_334_100_ _인쇄/출판/판촉(안내.표지판)");
        arrayCode.add("도서,비도서구입_334_110_ _도서/비도서");
        arrayCode.add("상품권_334_120_ _상품권");
        arrayCode.add("컴퓨터/사무기관련_336_000_ _");
        arrayCode.add("컴퓨터 소모품 설비_336_100_ _컴퓨터");
        arrayCode.add("사무용기기및보조용품_336_110_ _사무용품/문구");
        arrayCode.add("가전제품/냉난방.공조기.에어컨_338_000_ _");
        arrayCode.add("가전기기및 전자제품_338_100_ _전자제품");
        arrayCode.add("냉난방기.공조기.에어컨_338_200_ _냉난방기.공조기.에어컨");
        arrayCode.add("실험/계측기기/시험기_339_000_ _");
        arrayCode.add("실험실습기자재.계측기.시험기_339_100_ _실험실습");
        arrayCode.add("동식물_340_000_ _");
        arrayCode.add("동식물,어류,사료_340_100_ _동식물/사료/어류");
        arrayCode.add("장의/커튼/상패/잡화_344_000_ _");
        arrayCode.add("잡화관련_344_100_ _잡화");
        arrayCode.add("장의용품_344_110_ _장의");
        arrayCode.add("커튼 및 차양,블라인드_344_120_ _커텐/차양/블라인드");
        arrayCode.add("상패,기념패_344_130_ _상패/기념");
        arrayCode.add("기타_399_000_ _");
        arrayCode.add("기타 구매입찰_399_999_ _기타");
        arrayCode.add("컴퓨터관련_400_000_ _");
        arrayCode.add("컴퓨터_400_100_ _컴퓨터");
        arrayCode.add("토지/건물관련_401_000_ _");
        arrayCode.add("토지/건물_401_100_ _토지/건물");
        arrayCode.add("기계관련_402_000_ _");
        arrayCode.add("기계_402_100_ _기계");
        arrayCode.add("자동차관련_403_000_ _");
        arrayCode.add("자동차_403_100_ _자동차");
        arrayCode.add("기타매각(잡철.고철.비철.폐철.폐구리등)_404_000_ _");
        arrayCode.add("기타(잡철.고철.비철.폐철.폐구리등)_404_100_ _기타");

        int index = 0;
        ArrayList<BidUpCodeItem> arrayList = new ArrayList<>();
        for(int i = 0; i < arrayCode.size(); i++){
            String value = arrayCode.get(i);
            String[] splitedValue = value.split("_");
            String name = splitedValue[0];
            String code = splitedValue[1] + splitedValue[2];

            int bidType = Integer.parseInt(splitedValue[1]);
            BidUpCodeItem item = new BidUpCodeItem(name, code);
            if(splitedValue[2].equals("000")){
                if(bidType >= 400)
                    arraySellBidCodeParent.add(item);
                else if(bidType >= 300)
                    arrayServBidCodeParent.add(item);
                else if(bidType >= 200)
                    arrayPurcBidCodeParent.add(item);
                else
                    arrayConsBidCodeParent.add(item);
                if(i != 0){
                    if(bidType > 400)
                        mapSellBidCodeChild.put(arraySellBidCodeParent.get(index++), arrayList);
                    else if(bidType > 310)
                        mapServBidCodeChild.put(arrayServBidCodeParent.get(index++), arrayList);
                    else if(bidType > 210)
                        mapPurcBidCodeChild.put(arrayPurcBidCodeParent.get(index++), arrayList);
                    else
                        mapConsBidCodeChild.put(arrayConsBidCodeParent.get(index++), arrayList);

                    if(bidType == 210 || bidType == 310 || bidType == 400){
                        index = 0;
                    }
                    arrayList = new ArrayList<>();
                }
            }else{
                arrayList.add(item);
            }

            if(i == arrayCode.size() - 1){
                mapSellBidCodeChild.put(arraySellBidCodeParent.get(index++), arrayList);
            }
        }
    }

    static public ArrayList<BidUpCodeItem> getArrayConsBidCodeParent(){
        if(arrayConsBidCodeParent.size() == 0)
            makeArrayCode();
        return arrayConsBidCodeParent;
    }

    static public ArrayList<BidUpCodeItem> getArrayPurcBidCodeParent(){
        if(arrayPurcBidCodeParent.size() == 0)
            makeArrayCode();
        return arrayPurcBidCodeParent;
    }

    static public ArrayList<BidUpCodeItem> getArrayServBidCodeParent(){
        if(arrayServBidCodeParent.size() == 0)
            makeArrayCode();
        return arrayServBidCodeParent;
    }

    static public ArrayList<BidUpCodeItem> getArraySellBidCodeParent(){
        if(arraySellBidCodeParent.size() == 0)
            makeArrayCode();
        return arraySellBidCodeParent;
    }

    static public HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> getMapConsBidCodeChild(){
        return mapConsBidCodeChild;
    }

    static public HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> getMapPurcBidCodeChild(){
        return mapPurcBidCodeChild;
    }

    static public HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> getMapServBidCodeChild(){
        return mapServBidCodeChild;
    }

    static public HashMap<BidUpCodeItem, ArrayList<BidUpCodeItem>> getMapSellBidCodeChild(){
        return mapSellBidCodeChild;
    }

    static public class BidUpCodeItem{
        private String name;
        private String code;

        public BidUpCodeItem(String name, String code){
            this.code = code;
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        public String getCode(){
            return this.code;
        }
    }
}
