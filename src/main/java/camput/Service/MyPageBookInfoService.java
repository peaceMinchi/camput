package camput.Service;

import camput.Dto.MyPageCampDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyPageBookInfoService {

    Page<MyPageCampDto> bookedCamp(String memberId, Pageable pageable);
}
