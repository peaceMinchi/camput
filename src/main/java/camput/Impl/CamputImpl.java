package camput.Impl;

import camput.Dto.*;
import camput.Service.CamputService;
import camput.domain.*;
import camput.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CamputImpl implements CamputService {

    private final CamputRepository camputRepository;
    private final CommentedRepository commentedRepository;
    private final MemberRepository memberRepository;
    private final MemberLikedRepository memberLikedRepository;
    private final CampBookedImpl campBooked;
    private final MemberBookedImpl memberBooked;


    @Override
    @Transactional(readOnly = true)
    public DetailPageDto show(String campName,String memberId){
        Camput camp = camputRepository.findByCampName(campName);
        Member member = memberRepository.findByMemberLoginId(memberId);
        MemberLiked like = memberLikedRepository.findByMemberAndLikedCampName(member, campName);
        int resultLike =0;
        if(like==null){
            resultLike=0;
        }else{
            resultLike=1;
        }
        List<CampCommentDto> campCommentDtos = getCampCommentDtos(camp);
        List<String> prices = getPrices(camp);
        String address = camp.getCampAddress().getSimpleAddr();
        DetailPageDto campInfo = makeDetailPage(camp, campCommentDtos, prices, address,resultLike);
        return campInfo;
    }

    @Override
    public String bookedCamp(String memberId, String finalReservationDto) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jobj=(JSONObject) jsonParser.parse(finalReservationDto);
        String startReservationDay = (String)jobj.get("startReservationDay");
        LocalDate startDay=LocalDate.parse(startReservationDay, DateTimeFormatter.ISO_DATE);
        String endReservationDay = (String)jobj.get("endReservationDay");
        LocalDate endDay=LocalDate.parse(endReservationDay, DateTimeFormatter.ISO_DATE);
        String campName = (String)jobj.get("campName");
        int price =Integer.parseInt((String)jobj.get("price"));
        CampBooked cmBooked= campBooked.campBooking(memberId, campName, startDay,endDay,price);
        log.info("cmBooked={}",cmBooked.getId());
        if(cmBooked==null){
            return null;
        }
        MemberBooked memberBooked1 = memberBooked.makeMemberReservation(cmBooked.getId(), memberId);
        log.info("memberBooked1={}",memberBooked1.getId());

        return "complete";
    }

    private static DetailPageDto makeDetailPage(Camput camp, List<CampCommentDto> campCommentDtos, List<String> prices, String address,int resultLike) {
        log.info("makeDetailPage");
        DetailPageDto campInfo = DetailPageDto.builder()
                .campAddress(address)
                .simpleIntro(camp.getLineIntro())
                .detailIntro(camp.getIntro())
                .campContents(campCommentDtos)
                .campName(camp.getCampName())
                .like(resultLike)
                .image(camp.getCampImageFilesList().get(0).getCampOriginalUrl())
                .campTotalAvg(camp.getTotalStarAvg())
                .totalLike(camp.getMemberLikeTotalCount())
                .prices(prices)
                .id(camp.getId().toString())
                .build();

        return campInfo;
    }

    private static List<String> getPrices(Camput camp) {
        List<CampPrice> campPrices = camp.getCampPrices();
        List<String> prices = new ArrayList<>();

        for (CampPrice campPrice : campPrices) {
            prices.add(Integer.toString(campPrice.getPrice())) ;
        }
        return prices;
    }

    private List<CampCommentDto> getCampCommentDtos(Camput camp) {
        List<Commented> campComments = commentedRepository.findAllByCamput(camp);
        List<CampCommentDto> campCommentDtos = new ArrayList<>();
        if(commentedRepository.findAllByCamput(camp)!=null){

            for (Commented campComment : campComments) {
                CampCommentDto comment = CampCommentDto.builder()
                        .comment(campComment.getCommentedContent())
                        .makedDate(campComment.getCommentedDate())
                        .stars(campComment.getStars())
                        .memberName(campComment.getMember().getNickName())
                        .memberLoginId(campComment.getMember().getMemberLoginId())
                        .id(campComment.getId())
                        .build();
                campCommentDtos.add(comment);
            }
        }else{
            return null;
        }
        return campCommentDtos;
    }

    @Override
    public Camput findByCampName(String campName) {
        return camputRepository.findByCampName(campName);
    }

}
