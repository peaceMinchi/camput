package camput.repository;

import camput.domain.CampBooked;
import camput.domain.Camput;
import camput.domain.MemberBooked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CampBookedRepository extends JpaRepository<CampBooked,Long> {

    List<CampBooked> findAllByCamput(Camput camput);
    CampBooked findByMemberBooked(MemberBooked memberBooked);
    void deleteByCamputAndCampBookedDay(Camput camput, LocalDateTime bookedDay);
}
