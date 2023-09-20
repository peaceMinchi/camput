package camput.Dto.loginApiDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    private String loginId;
    private String nickname;
    private String gender;
    private String birthday;
@Builder

    public MemberResponseDto(String loginId, String nickname, String gender, String birthday) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
    }
}
