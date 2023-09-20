package camput.Dto.loginApiDto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.usertype.UserType;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginApiRequestDto {

    @NotNull
    private UserType userType;
    @NotNull
    private String code;
}
