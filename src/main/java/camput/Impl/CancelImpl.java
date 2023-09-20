package camput.Impl;

import camput.Dto.CancelShowInfoDto;
import camput.Service.CancelService;
import camput.domain.*;
import camput.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CancelImpl implements CancelService {
    private final MemberRepository memberRepository;
    private final MemberBookedRepository memberBookedRepository;
    private final CampBookedRepository campBookedRepository;
    private final CamputRepository camputRepository;
    private final CampReservationDaysRepository campReservationDaysRepository;
    @Override
    @Transactional(readOnly = true)
    public CancelShowInfoDto showCancelInfo(String campName, String loginId, String bookedDay) {
        LocalDateTime bDay=LocalDateTime.parse(bookedDay,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Member member = memberRepository.findByMemberLoginId(loginId);
        MemberBooked memberBooked = memberBookedRepository.findByBookedDayAndMember(bDay, member);
        CampBooked campBooked = campBookedRepository.findByMemberBooked(memberBooked);
        int afterPoint = Integer.parseInt(member.getMemberPoint()) + campBooked.getCampPrice();

        CancelShowInfoDto info = CancelShowInfoDto.builder()
                .campName(memberBooked.getBookedCampName())
                .memberName(member.getMemberName())
                .startReservationDay(memberBooked.getMStartDay())
                .endReservationDay(memberBooked.getMEndDay())
                .memberPhone(member.getPhoneNumber())
                .price(campBooked.getCampPrice())
                .bookedDay(campBooked.getCampBookedDay())
                .point(Integer.parseInt(member.getMemberPoint()))
                .afterMemberPoint(afterPoint)
                .build();

        return info;
    }
    @Override
    public String cancel(String cancelInfo, String loginId) throws ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONObject jobj=(JSONObject) jsonParser.parse(cancelInfo);
        LocalDateTime bookedDay = LocalDateTime.parse((String)jobj.get("bookedDay"),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Camput camp = camputRepository.findByCampName((String)jobj.get("campName"));
        Long jPrice=(Long)jobj.get("price");
        int price =jPrice.intValue();
        Member member = memberRepository.findByMemberLoginId(loginId);

        campBookedRepository.deleteByCamputAndCampBookedDay(camp,bookedDay);
        memberBookedRepository.deleteByMemberAndBookedDay(member,bookedDay);

        member.plusPoint(price);
        checkDelete(jobj, camp);
        return member.getMemberLoginId();
        }

    private void checkDelete(JSONObject jobj, Camput camp) {
        LocalDate startDay = LocalDate.parse((String) jobj.get("startReservationDay"),DateTimeFormatter.ISO_DATE);
        LocalDate endDay = LocalDate.parse((String) jobj.get("endReservationDay"),DateTimeFormatter.ISO_DATE);

        while(!startDay.isEqual(endDay)){
            String campName = camp.getCampName();
           CampReservationDays booked = campReservationDaysRepository.findByCampNameAndReservationDays(campName,startDay);
            CampReservationDays campReservationDays = booked.minusCount();
                if(campReservationDays.getCount()==0){
                    campReservationDaysRepository.deleteByCampNameAndReservationDays(camp.getCampName(),startDay);
                }else{
                    campReservationDaysRepository.save(campReservationDays);
                }
            startDay=startDay.plusDays(1);
            }
    }
}
