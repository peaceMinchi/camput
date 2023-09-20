package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindIdDto {

    private String nickName;
    private String phoneNumber;
    private String memberLoginId; // 찾아서 리턴할 로그인 아이디
    private String memberIdCheck; // 메세지

    @Builder
    public FindIdDto(String nickName, String phoneNumber, String memberLoginId, String memberIdCheck) {
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.memberLoginId = memberLoginId;
        this.memberIdCheck = memberIdCheck;
    }
}
