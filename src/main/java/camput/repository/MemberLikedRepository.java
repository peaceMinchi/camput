package camput.repository;

import camput.domain.Member;
import camput.domain.MemberLiked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLikedRepository  extends JpaRepository<MemberLiked,Long> {

    Page<MemberLiked> findByMember(Member member, Pageable pageable);
    MemberLiked findByMemberAndLikedCampName(Member member,String campName);
    void deleteById(Long id);
}
