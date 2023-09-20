package camput.Impl;

import camput.Dto.MyPageCampDto;
import camput.Service.MyPageBookInfoService;
import camput.domain.Member;
import camput.domain.MemberBooked;
import camput.repository.MemberBookedRepository;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MyPageBookInfoImpl implements MyPageBookInfoService {
    private final MemberRepository memberRepository;
    private final MemberBookedRepository memberBookedRepository;
    @Override
    public Page<MyPageCampDto> bookedCamp(String memberId, Pageable pageable) {
        Member member = memberRepository.findByMemberLoginId(memberId);
        int page =(pageable.getPageNumber()==0)?0:(pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 3);
        Page<MemberBooked> memberBookedCamps = memberBookedRepository.findByMember_Id(member.getId(), pageable);
        Page<MyPageCampDto> bookedCampDtos= memberBookedCamps.map(
                camp-> MyPageCampDto.builder()
                        .myPageCampDay(camp.getBookedDay())
                        .myPageCampEndDay(camp.getMStartDay())
                        .myPageCampStartDay(camp.getMStartDay())
                        .myPageCampAddress(camp.getBookedCampAddress())
                        .myPageCampName(camp.getBookedCampName())
                        .myPageCampImageUrl(camp.getBookedCampImageUrl())
                        .build());
            return bookedCampDtos;
    }
}
