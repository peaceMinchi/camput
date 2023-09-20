package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QnaAnswer {

    @Id
    @GeneratedValue
    @Column(name = "qnaanswer_id")
    private Long id;
    private String qAnswer;
    private Long qnaId;
    private String memberLoginId;

    private LocalDateTime reqActDate;
    @Builder
    public QnaAnswer(String qAnswer, Long qnaId, String memberLoginId, LocalDateTime reqActDate) {
        this.qAnswer = qAnswer;
        this.qnaId = qnaId;
        this.memberLoginId = memberLoginId;
        this.reqActDate = LocalDateTime.now();
    }

    public void QnaAnswerUpdate(Long id, String qAnswer) {
        this.id = id;
        this.qAnswer = qAnswer;
    }
}
