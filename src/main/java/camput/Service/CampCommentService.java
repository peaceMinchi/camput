package camput.Service;

import camput.Dto.CampCommentDto;
import camput.domain.Camput;
import camput.domain.Commented;

import java.util.List;
import java.util.Optional;

public interface CampCommentService {
    List<Commented> findAllByCamput(Camput camput);
//    CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate);
    void save(CampCommentDto commentDto); // 리뷰 저장

    void deleteById(Long id); // 삭제
//    double ReviewService(int stars);

    void update(CampCommentDto commentDto);

}
