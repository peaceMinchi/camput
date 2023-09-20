package camput.Service;

import camput.Dto.*;
import camput.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberService {
    MemberInfoDto findMemberInfo(String loginId);
    String findMemberNickName(String loginId);
    String findMemberByPw(String memberPw);
    //List<MemberBooked> findBookedList()

    // 로그인 및 세션
    // 로그인 아이디 있는지? 없는지?
    boolean loginIsValid(String memberLoginId, String memberPassword);

    List<Member> findAll();
    static Map<Long, Member> store = new HashMap<>();

    // 아이디 찾기
    FindIdDto findByNickNameAndPhoneNumber(FindIdDto findIdDto);
    // 아이디 인증
    boolean checkMemberLoginId(String memberLoginId);

    MemberPointDto memberPoint(String asd123,String price);

    String update(FindPwDto findPwDto);
}
