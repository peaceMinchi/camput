package camput.Dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@NoArgsConstructor // 기본생성자 자동 생성
@ToString   // 출력할때
public class MemberJoinDto {
    @NotNull
    private String memberEmail;
    @NotNull
    private String birthday;
    @NotNull
    private String memberName;
    @NotNull
    private String gender;
    @NotNull
    private String streetAddress;
    @NotNull
    private String mainAddress;
    @NotNull
    private String memberPostNum;
    @NotNull
    private String detailAddress;
    @NotNull
    private String extraAddress;
    @NotNull
    private String memberLoginId;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String memberPassword;
    @NotNull
    private String nickName;

    private String memberPoint;

    private Long id;

    private String memberPasswordHidden;

@Builder
    public MemberJoinDto(String memberName, String phoneNumber, String memberEmail, String birthday, String gender, String streetAddress, String mainAddress, String memberPostNum, String detailAddress, String extraAddress, String memberLoginId, String memberPassword, String nickName, String memberPoint, long id, String memberPasswordHidden) {
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.memberEmail = memberEmail;
        this.birthday = birthday;
        this.gender = gender;
        this.streetAddress = streetAddress;
        this.mainAddress = mainAddress;
        this.memberPostNum = memberPostNum;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
        this.nickName = nickName;
        this.memberPoint = memberPoint;
        this.id = id;
        this.memberPasswordHidden = memberPasswordHidden;
    }
}
