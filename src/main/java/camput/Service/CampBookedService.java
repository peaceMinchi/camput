package camput.Service;

import camput.Dto.DetailPageDto;
import camput.Dto.ReservationDto;
import camput.domain.CampBooked;

import java.time.LocalDate;

public interface CampBookedService {

    CampBooked campBooking(String loginId, String campName, LocalDate startDay, LocalDate endDay, int price);

}
