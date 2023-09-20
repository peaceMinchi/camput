package camput.Impl;

import camput.Dto.FinalReservationDto;
import camput.Dto.ReservationDto;
import camput.Service.CamputService;
import camput.domain.Member;
import camput.repository.CamputRepository;
import camput.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class CamputImplTest {

    @Autowired
    CamputService camputService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void Test(){
        Member member = memberRepository.findById(26482L).get();
        FinalReservationDto reservation = FinalReservationDto.builder()
                .startReservationDay("2023-01-30")
                .endReservationDay("2023-02-02")
                .campName("드림랜드오토캠핑장")
                .price("50000")
                .build();
        //String reservationDto = camputService.bookedCamp(member.getMemberLoginId(), reservation);

    }

}