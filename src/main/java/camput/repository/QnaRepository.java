package camput.repository;

import camput.Dto.QnADto;
import camput.domain.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QnaRepository  extends JpaRepository<Qna,Long> {
    Page<Qna> findByQnaTitleContainingOrQnaContentContainingOrMemberLoginIdContaining(Pageable pageable, String qnaTitle, String qnaContent, String memberLoginId);

    Page<Qna> findAll(Pageable pageable);

    @Modifying
    @Query("update Qna t1 set t1.viewCount = t1.viewCount + 1 where t1.id = :id")
    int viewCountUpdate(@Param("id") Long id);
}
