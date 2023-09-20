package camput.Impl;

import camput.Dto.MemberJoinDto;
import camput.Service.JoinService;
import camput.domain.Member;
import camput.domain.MemberAddress;
import camput.domain.MemberGender;
import camput.repository.MemberAddressRepository;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JoinServiceImpl implements JoinService {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    @Override
    @Transactional
    public void join(MemberJoinDto memberJoinDto) {
        validateDuplicateMember(memberJoinDto);
        MemberGender gender;
        MemberAddress address = MemberAddress.builder()
                .streetAddress(memberJoinDto.getStreetAddress())
                .mainAddress(memberJoinDto.getMainAddress())
                .memberPostNum(memberJoinDto.getMemberPostNum())
                .detailAddress(memberJoinDto.getDetailAddress())
                .extraAddress(memberJoinDto.getExtraAddress())
                .build();
        MemberAddress save = memberAddressRepository.save(address);

        if(memberJoinDto.getGender().equals("남")){
            gender = MemberGender.MALE;
        } else {
            gender = MemberGender.FEMALE;
        }

        Member member = Member.builder()
                .memberEmail(memberJoinDto.getMemberEmail())
                .birthday(memberJoinDto.getBirthday())
                .memberName(memberJoinDto.getMemberName())
                .gender(gender)
                .memberAddress(save)
                .memberLoginId(memberJoinDto.getMemberLoginId())
                .phoneNumber(memberJoinDto.getPhoneNumber())
                .memberPassword(memberJoinDto.getMemberPassword())
                .nickName(memberJoinDto.getNickName())
                .memberPoint("1000000")
                .build();
        memberRepository.save(member);
    }

    // 아이디 중복체크
    public void validateDuplicateMember(MemberJoinDto memberJoinDto){
        Member findMember = memberRepository.findByMemberLoginId(memberJoinDto.getMemberLoginId());
        if (findMember != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public String memberLoginCheck(String memberLoginCheck) {
        Member findMember = memberRepository.findByMemberLoginId(memberLoginCheck);
        if (findMember != null){ // 조회결과가 있다 ->함 사용할 수 없다.
            return null;
        }else {
            return "ok";
        }
    }

}
// 1. dot -> entity로 변환
// 2. repository의 save 메서드 호출
// repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)