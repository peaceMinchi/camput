package camput.Service;

import camput.Dto.MyPageCampDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MyPageLikeService {
     Page<MyPageCampDto> likeCamps(String memberId, Pageable pageable);
}
