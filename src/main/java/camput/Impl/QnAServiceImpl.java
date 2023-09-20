package camput.Impl;

import camput.Dto.QnADto;
import camput.Dto.QnaAnswerDto;
import camput.Service.QnAService;
import camput.domain.Qna;
import camput.domain.QnaAnswer;
import camput.repository.QnaAnswerRepository;
import camput.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

    private final QnaRepository qnaRepository;
    private final QnaAnswerRepository qnaAnswerRepository;

    public void save(QnADto qnaDto){
        Qna qna = Qna.builder()
                .qnaTitle(qnaDto.getQnaTitle())
                .qnaContent(qnaDto.getQnaContent())
                .qnaUpdateDate(LocalDateTime.now())
                .memberLoginId(qnaDto.getMemberLoginId())
                .build();
        qnaRepository.save(qna);
    }

    public List<Qna> viewQnAList(){
        return qnaRepository.findAll();
    }

    public Qna qnaView(Long id){
        return qnaRepository.findById(id).get();
    }

    public void qnaDelete(Long id){
        qnaRepository.deleteById(id);
    }

    public String qnaUpdate(QnADto qnaDto){
        Qna qna = qnaRepository.findById(qnaDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("qna 없다"));

        qna.qnaUpdate(
                qnaDto.getId(),
                qnaDto.getQnaTitle(),
                qnaDto.getQnaContent(),
                LocalDateTime.now(),
                qnaDto.getMemberLoginId()
        );
        qnaRepository.save(qna);
        // 엔티티에 메서드 만들어 줘야함
        return null;
    }

    @Override
    public Page<QnADto> qnaListWithPaging(Pageable pageable, QnADto qnaDto) {
        int page =(pageable.getPageNumber()==0)?0:(pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<Qna> list = null;
        if (qnaDto.getSearchKeyword() != null && qnaDto.getSearchKeyword() != "") {
            qnaDto.setQnaTitle(qnaDto.getSearchKeyword());
            qnaDto.setQnaContent(qnaDto.getSearchKeyword());
            qnaDto.setMemberLoginId(qnaDto.getSearchKeyword());
            list = qnaRepository.findByQnaTitleContainingOrQnaContentContainingOrMemberLoginIdContaining(pageable, qnaDto.getQnaTitle(), qnaDto.getQnaContent(), qnaDto.getMemberLoginId());
        } else {
            list = qnaRepository.findAll(pageable);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Page<QnADto> result = list.map(
            map-> QnADto.builder()
                        .id(map.getId())
                        .qnaTitle(map.getQnaTitle())
                        .qnaContent(map.getQnaContent())
                        .qnaUpdateDate(dateTimeFormatter.format(map.getQnaUpdateDate()))
                        .memberLoginId(map.getMemberLoginId())
                        .viewCount(map.getViewCount())
                        .build());
        return result;
    }

    @Override
    public int updateViewCount(Long id) {
        return qnaRepository.viewCountUpdate(id);
    }

    @Override
    public void insertQnaAnswer(QnADto qnaDto) {
        QnaAnswer qnaAnswer = QnaAnswer.builder()
                .qnaId(qnaDto.getId())
                .qAnswer(qnaDto.getQnaAnswer())
                .memberLoginId(qnaDto.getMemberLoginId())
                .build();

        qnaAnswerRepository.save(qnaAnswer);
    }

    @Override
    public List<QnaAnswerDto> selectQnaAnswer(Long id) {
        List<QnaAnswer> result = qnaAnswerRepository.findByQnaId(id);
        List<QnaAnswerDto> list = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (QnaAnswer qnaAnswer : result) {
            QnaAnswerDto dto = QnaAnswerDto.builder()
                    .id(qnaAnswer.getId())
                    .qnaId(qnaAnswer.getQnaId())
                    .memberLoginId(qnaAnswer.getMemberLoginId())
                    .reqActDate(dateTimeFormatter.format(qnaAnswer.getReqActDate()))
                    .qAnswer(qnaAnswer.getQAnswer())
                    .build();

            list.add(dto);
        }
        return list;
    }

    @Override
    public void qnaDeleteAnswer(Long id) {
        qnaAnswerRepository.deleteById(id);
    }

    @Override
    public void qnaUpdateAnswer(Long id, String qAnswer) {
        QnaAnswer qnaAnswer = qnaAnswerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("qna 없다"));

        qnaAnswer.QnaAnswerUpdate(
                id, qAnswer
        );

        qnaAnswerRepository.save(qnaAnswer);
    }


}
