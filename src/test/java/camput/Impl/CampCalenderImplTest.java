package camput.Impl;

import camput.Dto.FinalReservationDto;
import camput.Dto.ReservationDto;
import camput.Service.CampCalenderService;
import camput.Service.CamputService;
import camput.domain.Member;
import camput.repository.MemberBookedRepository;
import camput.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import camput.Service.CampCalenderService;
import camput.repository.MemberBookedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class CampCalenderImplTest {
    @Autowired
    EntityManager em;
    @Autowired
    CampCalenderService campCalenderService;
    @Autowired
    CamputService camputService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void Test(){
        Member member = memberRepository.findById(26482L).get();
        FinalReservationDto reservation1 = FinalReservationDto.builder()
                .startReservationDay("2023-01-30")
                .endReservationDay("2023-02-02")
                .campName("드림랜드오토캠핑장")
                .price("50000")
                .build();
        FinalReservationDto reservation2 = FinalReservationDto.builder()
                .startReservationDay("2023-01-30")
                .endReservationDay("2023-02-02")
                .campName("드림랜드오토캠핑장")
                .price("50000")
                .build();
        FinalReservationDto reservation3 = FinalReservationDto.builder()
                .startReservationDay("2023-01-30")
                .endReservationDay("2023-02-02")
                .campName("드림랜드오토캠핑장")
                .price("50000")
                .build();
   /*    ReservationDto reservation4 = ReservationDto.builder()
                .endDate(LocalDate.of(2023, 01, 30))
                .startDate(LocalDate.of(2023, 02, 2))
                .campName("드림랜드오토캠핑장")
                .choicePrice(50000)
                .memberLoginId(member.getMemberLoginId())
                .memberName(member.getMemberName())
                .build();
        ReservationDto reservation5 = ReservationDto.builder()
                .endDate(LocalDate.of(2023, 01, 30))
                .startDate(LocalDate.of(2023, 02, 2))
                .campName("드림랜드오토캠핑장")
                .choicePrice(50000)
                .memberLoginId(member.getMemberLoginId())
                .memberName(member.getMemberName())
                .build();*/

        /*camputService.bookedCamp(member.getMemberLoginId() , );
        camputService.bookedCamp(member.getMemberLoginId(),reservation2);
        camputService.bookedCamp(member.getMemberLoginId(), reservation3);*/
  /*     camputService.bookedCamp(member.getMemberLoginId(), "드림랜드오토캠핑장", reservation4);
        camputService.bookedCamp(member.getMemberLoginId(), "드림랜드오토캠핑장", reservation5);*/

        log.info("start1");
        List<LocalDate> blockDays = campCalenderService.campBookedCalender("드림랜드오토캠핑장");

        log.info("start2={}",blockDays.size());
        for (LocalDate blockDay : blockDays) {
            log.info("start3");
            log.info("blockDay={}",blockDay);
        }

    }
}