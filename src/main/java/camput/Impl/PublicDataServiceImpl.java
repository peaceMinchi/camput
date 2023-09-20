package camput.Impl;

import camput.Service.PublicDataService;
import camput.domain.*;
import camput.repository.*;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PublicDataServiceImpl implements PublicDataService {

    private final CamputRepository camputCampRepository;
    private final CampAddressRepository campAddressRepository;
    private final CampDateInfoRepository campDateInfoRepository;
    private final CampImageFileRepository campImageFileRepository;
    private final CampMapRepository campMapRepository;
    private final FacilitiesRepository facilitiesRepository;
    private final CampPriceRepository campPriceRepository;

    @Override
    public void init(String jsonData){
        log.info("실행");
        try{
            JSONObject jObj;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            JSONObject response = (JSONObject) jsonObj.get("response");
            JSONObject body = (JSONObject) response.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray item = (JSONArray) items.get("item");

            for(int i=0;i<item.size();i++){
                jObj =(JSONObject)item.get(i);

                CampImageFiles firstImageUrl = saveFirstImageUrl(jObj);
                CampImageFiles campImageFiles = campImageFileRepository.save(firstImageUrl);
                CampDateInfo campDateInfo = saveBuild(jObj);
                CampDateInfo dateInfo = campDateInfoRepository.save(campDateInfo);
                CampAddress campAddress = saveCampAddress(jObj);
                CampAddress address = campAddressRepository.save(campAddress);
                CampMap campMap = saveCampMap(jObj);
                CampMap map = campMapRepository.save(campMap);
                Facilities facilities = saveFacilit(jObj);
                Facilities savedFacilities = facilitiesRepository.save(facilities);
                List<CampImageFiles> campImageFilesList= new ArrayList<>(Arrays.asList(campImageFiles));
                Camput camp = saveCamp(jObj, dateInfo, address, map, savedFacilities, campImageFilesList);
                camputCampRepository.save(camp);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Camput saveCamp(JSONObject jObj, CampDateInfo dateInfo, CampAddress address, CampMap map, Facilities savedFacilities, List<CampImageFiles> campImageFilesList) {
        List<CampPrice> campPrices =  saveCampPrice();
        return Camput.builder().campName(jObj.get("facltNm").toString())
                .animalCmCl(jObj.get("animalCmgCl").toString()).campAddress(address).campDateInfo(dateInfo)
                .campMap(map).campImageFilesList(campImageFilesList).caravanAccept(jObj.get("caravSiteCo").toString())
                .glampingAccept(jObj.get("glampSiteCo").toString()).checkDoNm(jObj.get("doNm").toString())
                .campReservationMethod(jObj.get("resveCl").toString()).campReservationPage(jObj.get("resveUrl").toString())
                .facilities(savedFacilities).feature(jObj.get("featureNm").toString()).homePage(jObj.get("homepage").toString())
                .intro(jObj.get("intro").toString()).lineIntro(jObj.get("lineIntro").toString()).campPrices(campPrices)
                .tel(jObj.get("tel").toString()).reservationCount(10).memberLikeTotalCount(0)
                .totalStarAvg(1L).build();
    }

    private static Facilities saveFacilit(JSONObject jObj) {
        return Facilities.builder()
                .brazer(jObj.get("brazierCl").toString())
                .extinguisher(jObj.get("extshrCo").toString())
                .subStructure(jObj.get("sbrsCl").toString())
                .waterPlace(jObj.get("wtrplCo").toString())
                .build();
    }

    private static CampMap saveCampMap(JSONObject jObj) {
        return CampMap.builder()
                .mapX(jObj.get("mapX").toString())
                .mapY(jObj.get("mapY").toString())
                .build();
    }

    private static CampAddress saveCampAddress(JSONObject jObj) {
        return CampAddress.builder()
                .detailAddr(jObj.get("addr2").toString())
                .simpleAddr(jObj.get("addr1").toString())
                .doNm(jObj.get("doNm").toString())
                .sigunNum(jObj.get("sigunguNm").toString())
                .zipeCode(jObj.get("zipcode").toString())
                .build();
    }
    private static CampDateInfo saveBuild(JSONObject jObj) {
        return CampDateInfo.builder()
                .notWorkingDay(jObj.get("hvofEnddle").toString())
                .openDeCl(jObj.get("operDeCl").toString())
                .openSessions(jObj.get("operPdCl").toString())
                .build();
    }
    private static CampImageFiles saveFirstImageUrl(JSONObject jObj) {
        return CampImageFiles.builder()
                .campImageDate(LocalDateTime.now())
                .campOriginalUrl(jObj.get("firstImageUrl").toString())
                .build();
    }
    private List<CampPrice> saveCampPrice(){
        int price=50000;
        List<CampPrice> campPrices= new ArrayList<>();
        for (int i= 0;i<2;i++){
            CampPrice newPrice = CampPrice.builder()
                    .price(price)
                    .build();
            CampPrice campPrice = campPriceRepository.save(newPrice);
            campPrices.add(campPrice);
            price +=50000;
        }
        return campPrices;
    }

}
