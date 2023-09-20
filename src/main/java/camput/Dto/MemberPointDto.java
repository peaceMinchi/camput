package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPointDto {
    private String point;
    private String memberName;
    private String afterMemberPoint;
    private String memberPhone;

    @Builder
    public MemberPointDto(String point, String memberName, String afterMemberPoint, String memberPhone) {
        this.point = point;
        this.memberName = memberName;
        this.afterMemberPoint = afterMemberPoint;
        this.memberPhone=memberPhone;
    }
}
