package camput.Service;

import camput.Dto.BookedDayDto;
import camput.Dto.CampCommentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface CampCalenderService {
   List<LocalDate> campBookedCalender(String campName);
}
