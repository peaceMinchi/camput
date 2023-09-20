package camput.repository;

import camput.domain.Member;
import camput.domain.MemberBooked;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MemberBookedRepository extends JpaRepository<MemberBooked, Long> {

    MemberBooked findByBookedDayAndMember(LocalDateTime bDay,Member member);
    Page<MemberBooked> findByMember_Id(Long memberId, Pageable pageable);
    void deleteByMemberAndBookedDay(Member member, LocalDateTime bookedDay);


}
