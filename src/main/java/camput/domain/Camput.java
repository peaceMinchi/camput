package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Camput {
    @Id
    @GeneratedValue
    @Column(name = "camput_id")
    private Long id;
    private String campName;
    @Column(length = 700)
    private String lineIntro;
    @Column(length = 9000)
    private String intro;
    private String animalCmCl;
    private String campReservationMethod;
    @Column(length = 700)
    private String campReservationPage;
    private String homePage;
    private String checkDoNm;
    private String tel;
    @Column(length = 700)
    private String feature;
    private int reservationCount;
    private int memberLikeTotalCount  ;
    private Long totalStarAvg;
    private String caravanAccept;
    private String glampingAccept;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private CampMap campMap;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CampImageFiles> campImageFilesList = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private CampDateInfo campDateInfo;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private CampAddress campAddress;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Facilities facilities;
    @OneToMany
    private List<CampPrice> campPrices;
    @Builder
    public Camput(Long id, String campName, String lineIntro, String intro, String animalCmCl, String campReservationMethod, String campReservationPage, String homePage, String tel, String feature, int reservationCount, int memberLikeTotalCount, Long totalStarAvg, String caravanAccept,String checkDoNm, String glampingAccept, CampMap campMap, List<CampImageFiles> campImageFilesList, CampDateInfo campDateInfo, CampAddress campAddress,  Facilities facilities,List<CampPrice> campPrices) {
        this.id = id;
        this.campName = campName;
        this.lineIntro = lineIntro;
        this.intro = intro;
        this.animalCmCl = animalCmCl;
        this.campReservationMethod = campReservationMethod;
        this.campReservationPage = campReservationPage;
        this.homePage = homePage;
        this.tel = tel;
        this.feature = feature;
        this.reservationCount = reservationCount;
        this.memberLikeTotalCount = memberLikeTotalCount;
        this.totalStarAvg = totalStarAvg;
        this.caravanAccept = caravanAccept;
        this.glampingAccept = glampingAccept;
        this.checkDoNm=checkDoNm;
        this.campMap = campMap;
        this.campImageFilesList = campImageFilesList;
        this.campDateInfo = campDateInfo;
        this.campAddress = campAddress;
        this.facilities = facilities;
        this.campPrices=campPrices;
    }

    public Camput addLike(){
        this.memberLikeTotalCount+=1;
        return this;
    }
    public Camput minusLike(){
        this.memberLikeTotalCount-=1;
        return this;
    }
}
