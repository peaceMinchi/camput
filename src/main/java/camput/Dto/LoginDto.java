package camput.Dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Builder
public class LoginDto {

    @NotBlank
    private String memberLoginId;
    @NotBlank
    private String memberPassword;

    @Builder
    public LoginDto(String memberLoginId, String memberPassword) {
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
    }
}
