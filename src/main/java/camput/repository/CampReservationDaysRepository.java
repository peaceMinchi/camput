package camput.repository;

import camput.domain.CampBooked;
import camput.domain.CampReservationDays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CampReservationDaysRepository extends JpaRepository<CampReservationDays,Long> {
    List<CampReservationDays> findAllByReservationDaysAndCampName(LocalDate reservationDay,String campBooked);
    List<CampReservationDays> findByCampNameAndCount(String campName,int count);
    void deleteByCampNameAndReservationDays(String campName, LocalDate day);
    //@Query("select c from CampReservationDays c where c.reservationDays = :day And c.campName = :campName")
    CampReservationDays findByCampNameAndReservationDays(String campName, LocalDate day);

}
