package camput.Dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter@Setter
@NoArgsConstructor // 기본생성자 자동 생성
@ToString
public class FindPwDto {
    @NotBlank
    private String memberLoginId;
    @NotBlank
    private String memberPassword;
    private Long id;
    private String memberPasswordHidden;

    @Builder
    public FindPwDto(String memberLoginId, String memberPassword, Long id, String memberPasswordHidden) {
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
        this.id = id;
        this.memberPasswordHidden = memberPasswordHidden;
    }
}
