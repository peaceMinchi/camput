package camput.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QnADto {

    private Long id;
    private String qnaTitle;
    private String qnaContent;
    private String qnaUpdateDate;
    private String memberLoginId;
    private String qnaAnswer;
    private String searchKeyword;
    private int viewCount;

    @Builder
    public QnADto(long id, String qnaTitle, String qnaContent, String qnaUpdateDate, String memberLoginId, String qnaAnswer, String searchKeyword, int viewCount){
        this.id = id;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaUpdateDate = qnaUpdateDate;
        this.memberLoginId = memberLoginId;
        this.qnaAnswer = getQnaAnswer();
        this.searchKeyword = searchKeyword;
        this.viewCount = viewCount;
    }
}
