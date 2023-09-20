package camput.Impl;
import camput.Service.CampCalenderService;
import camput.domain.CampReservationDays;
import camput.repository.CampReservationDaysRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CampCalenderImpl implements CampCalenderService {
    private final CampReservationDaysRepository campReservationDaysRepository;

    @Override
    public List<LocalDate> campBookedCalender(String campName) {

        List<LocalDate> resultDays = new ArrayList<>();
        List<CampReservationDays> fullReservationDay = campReservationDaysRepository.findByCampNameAndCount(campName, 3);
        if(!fullReservationDay.isEmpty()){
            for (CampReservationDays campReservationDays : fullReservationDay) {
                resultDays.add(campReservationDays.getReservationDays());
            }
        }else{
            return null;
        }
        return resultDays;
    }

}




