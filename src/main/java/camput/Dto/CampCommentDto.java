package camput.Dto;

import camput.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CampCommentDto {
    private Long id; // 리스트 불러올때 쓰기
    private LocalDateTime makedDate; // 날짜
    private String comment; // 내용
    private String memberName; // 닉네임
    private int stars; // 별점
    //private String saveImageUrl; // 사진
    //private String imageOriginalUrl;
    //private String imageFilename;
    //private LocalDateTime imageDate;
    private Member member;
    private Long camputId;
    private String campName;
    private String memberLoginId;

    @Builder
    public CampCommentDto(Long id, LocalDateTime makedDate, String comment, String memberName, int stars, String saveImageUrl, String imageOriginalUrl, String imageFilename, LocalDateTime imageDate, Member member, Long camputId, String memberLoginId) {
        this.id = id;
        this.makedDate = LocalDateTime.now();
        this.comment = comment;
        this.memberName = memberName;
        this.stars = stars;
        //this.saveImageUrl = saveImageUrl;
        //this.imageOriginalUrl = imageOriginalUrl;
        //this.imageFilename = imageFilename;
        //this.imageDate = imageDate;
        this.member = member;
        this.camputId = camputId;
        this.memberLoginId = memberLoginId;
    }
}
