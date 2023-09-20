package camput.repository;

import camput.domain.QnaAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaAnswerRepository  extends JpaRepository<QnaAnswer,Long> {
    List<QnaAnswer> findByQnaId(Long id);
}
