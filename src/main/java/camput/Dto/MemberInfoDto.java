package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoDto {

private String memberEmail;
private String memberBirth;
private String name;
private String gender;
private String memberAddress;
private String memberLoginId;
private String memberPhoneNum;
private Long id;
private String nickName;

@Builder
    public MemberInfoDto(String name, String memberPhoneNum, String memberEmail, String memberBirth, String gender, String memberAddress, String memberLoginId, Long id, String nickName) {
        this.name=name;
        this.memberPhoneNum=memberPhoneNum;
        this.memberEmail = memberEmail;
        this.memberBirth = memberBirth;
        this.gender = gender;
        this.memberAddress = memberAddress;
        this.memberLoginId = memberLoginId;
        this.id = id;
        this.nickName = nickName;
    }
}
