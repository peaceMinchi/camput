package camput.Impl;

import camput.Dto.ReservationDto;
import camput.Service.MemberBookedService;
import camput.domain.CampBooked;
import camput.domain.Member;
import camput.domain.MemberBooked;
import camput.repository.CampBookedRepository;
import camput.repository.CamputRepository;
import camput.repository.MemberBookedRepository;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberBookedImpl implements MemberBookedService {
    private final CampBookedRepository campBookedRepository;
    private final MemberBookedRepository memberBookedRepository;
    private final MemberRepository memberRepository;
    private final CamputRepository camputRepository;


    public MemberBooked makeMemberReservation(Long campBookedId,String memberId){
        Member member = memberRepository.findByMemberLoginId(memberId);
        CampBooked campBooked = campBookedRepository.findById(campBookedId).orElseThrow(() ->
                new IllegalArgumentException("등록되지 않은 에약입니다"));
        int campPrice = campBooked.getCampPrice();
        Member minusedPointMember = member.minusPoint(campPrice);
        MemberBooked memberBooked = MemberBooked.builder()
                .bookedCampName(campBooked.getCamput().getCampName())
                .member(minusedPointMember)
                .bookedDay(campBooked.getCampBookedDay())
                .mStartDay(campBooked.getCStartDay())
                .mEndDay(campBooked.getCEndDay())
                .bookedCampImageUrl(campBooked.getCamput().getCampImageFilesList().get(0).getCampOriginalUrl())
                .bookedCampAddress(campBooked.getCamput().getCampAddress().getSimpleAddr())
                .build();
        MemberBooked saveMemberBooked = memberBookedRepository.save(memberBooked);
        campBooked.addMemberBooked(saveMemberBooked);

        return saveMemberBooked;
    }

}
