package camput.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QnaAnswerDto {

    private Long id;
    private String qAnswer;
    private Long qnaId;
    private String memberLoginId;
    private String reqActDate;

    @Builder
    public QnaAnswerDto(long id, String qAnswer, Long qnaId, String memberLoginId, String reqActDate) {
       this.id = id;
       this.qAnswer = qAnswer;
       this.qnaId = qnaId;
       this.memberLoginId = memberLoginId;
       this.reqActDate = reqActDate;
    }
}
