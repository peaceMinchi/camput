package camput.repository;

import camput.Dto.CampCommentDto;
import camput.domain.Camput;
import camput.domain.Commented;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentedRepository extends JpaRepository<Commented,Long> {
    List<Commented> findAllByCamput(Camput camp);

    Optional<Commented> findById(Long id);


//    CampCommentDto findByNickNameAndMakedDate(String nickName, String makedDate);

    CampCommentDto save(CampCommentDto commentDto);

//    @Query("SELECT AVG(com.rating) FROM Commented com")
//    double getAvgRating();

}
