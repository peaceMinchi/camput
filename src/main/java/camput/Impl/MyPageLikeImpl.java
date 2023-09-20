package camput.Impl;

import camput.Dto.MyPageCampDto;
import camput.Service.MyPageLikeService;
import camput.domain.Member;
import camput.domain.MemberLiked;
import camput.repository.MemberLikedRepository;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MyPageLikeImpl implements MyPageLikeService {
    private final MemberLikedRepository memberLikedRepository;
    private final MemberRepository memberRepository;
    @Override
    public Page<MyPageCampDto> likeCamps(String memberId, Pageable pageable) {
        Member member = memberRepository.findByMemberLoginId(memberId);
        log.info("memberName={}",member.getMemberPoint());
        int page =(pageable.getPageNumber()==0)?0:(pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 3);
        Page<MemberLiked> likeCamp = memberLikedRepository.findByMember(member, pageable);
        log.info("likeCamp={}",likeCamp);

        Page<MyPageCampDto> likeCampDto= likeCamp.map(camp->
            MyPageCampDto.builder()
                    .myPageCampName(camp.getLikedCampName())
                    .myPageCampAddress(camp.getLikedCampAddress())
                    .myPageCampImageUrl(camp.getLikedCampImageUrl())
                    .build());
        return likeCampDto;
    }
}
