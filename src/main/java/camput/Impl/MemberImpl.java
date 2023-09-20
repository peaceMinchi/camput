package camput.Impl;
import camput.Dto.FindIdDto;
import camput.Dto.LoginDto;
import camput.Dto.FindIdDto;
import camput.Dto.LoginDto;
import camput.Dto.LoginDto;
import camput.Dto.MemberInfoDto;
import camput.Dto.*;
import camput.Service.MemberService;
import camput.domain.Member;
import camput.domain.MemberAddress;
import camput.domain.MemberGender;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberPointDto memberPoint(String loginId,String price) {
        Member member = memberRepository.findByMemberLoginId(loginId);
        int memberPoint =Integer.parseInt(member.getMemberPoint());
        int reservationPrice =Integer.parseInt(price);
        String afterMemberPoint = Integer.toString(memberPoint-reservationPrice);
        MemberPointDto memberPointDto = MemberPointDto.builder()
                .point(member.getMemberPoint())
                .memberName(member.getMemberName())
                .afterMemberPoint(afterMemberPoint)
                .memberPhone(member.getPhoneNumber())
                .build();
        return memberPointDto;
    }

    @Override
    public String update(FindPwDto findPwDto) {
        String memberPassword = null;
        if (findPwDto.getMemberPassword().equals("")) {
            memberPassword = findPwDto.getMemberPasswordHidden();
        } else{
            memberPassword = findPwDto.getMemberPassword();
        }
        Member member = memberRepository.findByMemberLoginId(findPwDto.getMemberLoginId());
        member.pwUpdateMember(memberPassword);
        memberRepository.save(member);
        return null;
    }

    @Override
    public String findMemberNickName(String loginId) {
        Member member = memberRepository.findByMemberLoginId(loginId);
        String nickName = member.getNickName();
        return nickName;
    }

    @Override
    public MemberInfoDto findMemberInfo(String loginId) {
        Member member = memberRepository.findByMemberLoginId(loginId);
        String memberHolAddress = getMemberAddress(member);
        String gender;
        if (member.getGender() == MemberGender.MALE) {
            gender = "남";
        } else {
            gender = "여";
        }
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .memberAddress(memberHolAddress)
                .memberEmail(member.getMemberEmail())
                .name(member.getMemberName())
                .gender(gender)
                .memberLoginId(member.getMemberLoginId())
                .memberBirth(member.getBirthday())
                .memberPhoneNum(member.getPhoneNumber())
                .id(member.getId())
                .nickName(member.getNickName())
                .build();

        return memberInfoDto;
    }

    @Override
    public String findMemberByPw(String memberPw) {
        Member member = memberRepository.findByMemberPassword(memberPw);
        if (member != null) {
            return "notFind";
        } else {
            return null;
        }
    }
    // 로그인 - 참이냐 거짓이냐로 분류
    @Override
    public boolean loginIsValid(String memberLoginId, String memberPassword) {
        Member member = memberRepository.findByMemberLoginIdAndMemberPassword(memberLoginId, memberPassword);
        if(member == null){
            return false;
        }
            return true;
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    // 아이디 찾기
    @Override
    public FindIdDto findByNickNameAndPhoneNumber(FindIdDto findIdDto) {
        Member member;
        if (memberRepository.findByNickNameAndPhoneNumber(findIdDto.getNickName(), findIdDto.getPhoneNumber()) == null) {
            String msg = "아이디가 없습니다.";
            return findIdDto.builder().memberIdCheck(msg).build();
        } else {
            member = memberRepository.findByNickNameAndPhoneNumber(findIdDto.getNickName(), findIdDto.getPhoneNumber());
            String memberLoginId = member.getMemberLoginId(); // 아이디 전환
            return FindIdDto.builder().memberLoginId(memberLoginId).build();
        }

    }


    private static String getMemberAddress(Member member) {
        MemberAddress memberAddress = member.getMemberAddress();
        String mainAddress = memberAddress.getMainAddress();
        String streetAddress = memberAddress.getStreetAddress();
        String detailAddress = memberAddress.getDetailAddress();
        String memberPostNum = memberAddress.getMemberPostNum();
        String memberHolAddress =
                mainAddress + " " + streetAddress + " " + detailAddress + " " + memberPostNum + " " + "(" + memberPostNum + ")";
        return memberHolAddress;
    }
    @Override
    public boolean checkMemberLoginId(String memberLoginId) {
        Member member = memberRepository.findByMemberLoginId(memberLoginId);
        System.out.println(member);
        if (member == null) {   // member에 아이디가 없으면 애가 출력됨
            return false;
        }
        return true;    // member에 아이디 있으면 애가 출력됨
    }
    }

