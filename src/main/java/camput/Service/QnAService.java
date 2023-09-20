package camput.Service;

import camput.Dto.QnADto;
import camput.Dto.QnaAnswerDto;
import camput.domain.Qna;
import camput.domain.QnaAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnAService {

    // 글작성
    void save(QnADto qnaDto);

    // 게시글 리스트 처리
    List<Qna> viewQnAList();

    // 특정 게시글 불러오기(상세보기)
    Qna qnaView(Long id);

    // 특정 게시글 삭제
    void qnaDelete(Long id);

    String qnaUpdate(QnADto qnaDto);

    Page<QnADto> qnaListWithPaging(Pageable pageable, QnADto qnADto);

    @Transactional
    int updateViewCount(Long id);

    @Transactional
    void insertQnaAnswer(QnADto qnaDto);

    List<QnaAnswerDto> selectQnaAnswer(Long id);

    void qnaDeleteAnswer(Long id);

    void qnaUpdateAnswer(Long id, String qAnswer);
}
