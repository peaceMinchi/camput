package camput;
import camput.Dto.ReservationDto;
import camput.Impl.CampBookedImpl;
import camput.Impl.MemberBookedImpl;
import camput.Service.CampCalenderService;
import camput.Service.CamputService;
import camput.domain.*;
import camput.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
@Slf4j
public class TestMember {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final CamputRepository camputRepository;
    private final MemberBookedRepository memberBookedRepository;
    private final MemberLikedRepository memberLikedRepository;
    private final CampAddressRepository campAddressRepository;
    private final CampImageFileRepository campImageFileRepository;
    private final CampCalenderService campCalenderService;
    private final CamputService camputService;

    /*@PostConstruct
    public void init() {
        MemberAddress address = MemberAddress.builder()
                .extraAddress("")
                .detailAddress("아파드 111호")
                .mainAddress("서울 강남")
                .memberPostNum("123")
                .streetAddress("xx로 323")
                .build();
        MemberAddress save = memberAddressRepository.save(address);

        Member makeMember = Member.builder()
                .memberName("User")
                .memberLoginId("asd123")
                .memberEmail("asd123@naver.com")
                .gender(MemberGender.MALE)
                .memberAddress(save)
                .birthday("001212")
                .nickName("one")
                .memberPoint("10000000")
                .phoneNumber("01012341234")
                .memberPassword("12345")
                .build();
        Member member = memberRepository.save(makeMember);

        Camput camput1 = camputRepository.findById(8L).get();
        log.info("camput1={}",camput1.getCampName());
        Camput camput2 = camputRepository.findById(16L).get();
        log.info("camput2={}",camput2.getCampName());
        CampAddress byId1 = campAddressRepository.findById(camput1.getCampAddress().getId()).get();
        CampAddress byId = campAddressRepository.findById(camput2.getCampAddress().getId()).get();
        List<CampImageFiles> all1 = campImageFileRepository.findAll();

        for(int i=0;i<10;i++){


        MemberLiked memberLiked = MemberLiked.builder()
                .likedCampImageUrl(all1.get(3).getCampOriginalUrl())
                .likedCampName(camput1.getCampName())
                .likedCampAddress(byId1.getSimpleAddr())
                .member(member)
                .build();

        MemberLiked memberLiked1 = MemberLiked.builder()
                .likedCampAddress(byId.getSimpleAddr())
                .likedCampImageUrl(all1.get(1).getCampOriginalUrl())
                .likedCampName(camput2.getCampName())
                .member(member)
                .build();

        memberLikedRepository.save(memberLiked1);
        memberLikedRepository.save(memberLiked);

        MemberBooked BCamp1 = MemberBooked.builder()
                .member(member)
                .bookedCampAddress("a어딘가1")
                .bookedCampName("캠프1")
                .mStartDay(LocalDate.of(2023, 12, 23))
                .mEndDay(LocalDate.of(2023, 12,25))
                .mBookedDay(LocalDateTime.of(2023,12,21,11,22,33))
                .bookedCampImageUrl(all1.get(3).getCampOriginalUrl())
                .build();
        MemberBooked BCamp2 = MemberBooked.builder()
                .member(member)
                .bookedCampAddress("a어딘가2")
                .bookedCampName("캠프2")
                .mStartDay(LocalDate.of(2023, 12, 24))
                .mEndDay(LocalDate.of(2023, 12,25))
                .mBookedDay(LocalDateTime.of(2023,12,21,11,22,33))
                .bookedCampImageUrl(all1.get(1).getCampOriginalUrl())
                .build();

        MemberBooked save1 = memberBookedRepository.save(BCamp1);
        MemberBooked save2 = memberBookedRepository.save(BCamp2);
        }
    }*/
   /*@PostConstruct
    void init(){
        MemberAddress address = MemberAddress.builder()
                .extraAddress("")
                .detailAddress("아파드 111호")
                .mainAddress("서울 강남")
                .memberPostNum("123")
                .streetAddress("xx로 323")
                .build();
        MemberAddress save = memberAddressRepository.save(address);

        Member makeMember = Member.builder()
                .memberName("User")
                .memberLoginId("asd123")
                .memberEmail("asd123@naver.com")
                .gender(MemberGender.MALE)
                .memberAddress(save)
                .birthday("001212")
                .nickName("one")
                .memberPoint("10000000")
                .phoneNumber("01012341234")
                .memberPassword("12345")
                .build();
        Member member = memberRepository.save(makeMember);
    }*/
}
