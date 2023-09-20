package camput.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Qna {

    @Id
    @GeneratedValue
    @Column(name = "qna_id")
    private Long id;
    private String qnaTitle;
    private String qnaContent;
    private LocalDateTime qnaUpdateDate;
    private String memberLoginId;
    @OneToOne
    private QnaAnswer qnaAnswer;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int viewCount;

    @Builder
    public Qna(String qnaTitle, String qnaContent, LocalDateTime qnaUpdateDate, QnaAnswer qnaAnswer, String memberLoginId, int viewCount) {
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaUpdateDate = qnaUpdateDate;
        this.qnaAnswer = qnaAnswer;
        this.memberLoginId = memberLoginId;
        this.viewCount = viewCount;
    }

    public void qnaUpdate(long id, String qnaTitle, String qnaContent, LocalDateTime qnaUpdateDate, String memberLoginId){
        this.id = id;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaUpdateDate = qnaUpdateDate;
        this.memberLoginId = memberLoginId;
    }
}
