package camput.Impl;

import camput.Dto.MemberJoinDto;
import camput.Service.MemberUpdateService;
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
public class MemberUpdateImpl implements MemberUpdateService {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;

    @Override
    @Transactional
    public MemberJoinDto view(String loginId) {
        Member member = memberRepository.findByMemberLoginId(loginId);
        String gender = "";
        if (member.getGender() != null) {
            if (member.getGender() == MemberGender.MALE) {
                gender = "남";
            } else {
                gender = "여";
            }
        }
        MemberJoinDto result = MemberJoinDto.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .birthday(member.getBirthday())
                .memberName(member.getMemberName())
                .gender(gender)
                .memberLoginId(member.getMemberLoginId())
                .phoneNumber(member.getPhoneNumber())
                .memberPassword(member.getMemberPassword())
                .nickName(member.getNickName())
                .memberPoint(member.getMemberPoint())
                .streetAddress(member.getMemberAddress().getStreetAddress())
                .mainAddress(member.getMemberAddress().getMainAddress())
                .memberPostNum(member.getMemberAddress().getMemberPostNum())
                .detailAddress(member.getMemberAddress().getDetailAddress())
                .extraAddress((member.getMemberAddress().getExtraAddress()))
                .build();
        return result;
    }

    @Transactional
    public String update(MemberJoinDto memberJoinDto){

        String memberPassword = null;
        if (memberJoinDto.getMemberPassword().equals("")) {
            memberPassword = memberJoinDto.getMemberPasswordHidden();
        } else{
            memberPassword = memberJoinDto.getMemberPassword();
        }
        System.out.println(memberJoinDto);
        MemberGender gender;
        if(memberJoinDto.getGender().equals("남")){
            gender = MemberGender.MALE;
        } else {
            gender = MemberGender.FEMALE;
        }
        MemberAddress address = MemberAddress.builder()
                .streetAddress(memberJoinDto.getStreetAddress())
                .mainAddress(memberJoinDto.getMainAddress())
                .memberPostNum(memberJoinDto.getMemberPostNum())
                .detailAddress(memberJoinDto.getDetailAddress())
                .extraAddress(memberJoinDto.getExtraAddress())
                .build();
        MemberAddress save = memberAddressRepository.save(address);

        Member member = memberRepository.findByMemberLoginId(memberJoinDto.getMemberLoginId());
        member.updateMember(
                memberJoinDto.getMemberEmail(),
                memberJoinDto.getNickName(),
                memberJoinDto.getMemberName(),
                memberJoinDto.getPhoneNumber(),
                memberJoinDto.getBirthday(),
                gender,
                save,
                memberPassword
                );
        memberRepository.save(member);
        return null;
    }
}
