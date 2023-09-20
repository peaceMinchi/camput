package camput.Service;

import camput.Dto.CancelShowInfoDto;
import org.json.simple.parser.ParseException;

public interface CancelService {

    CancelShowInfoDto showCancelInfo(String campName, String loginId,String bookedDay);

    String cancel(String cancelInfo, String loginId) throws ParseException;
}
