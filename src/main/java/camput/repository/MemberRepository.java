package camput.repository;

import camput.Dto.LoginDto;
import camput.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByMemberPassword(String memberPw);

    Member findByMemberLoginIdAndMemberPassword(String memberLoginId, String memberPassword);

    Member findByMemberLoginId(String memberLoginId);

    Member findByNickNameAndPhoneNumber(String nickName, String phoneNumber);

}

