package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAddress {
    @Id@GeneratedValue
    @Column(name="memberAddress_id")
    private Long id;

    private String streetAddress;

    private String mainAddress;

    private String memberPostNum;

    private String detailAddress;

    private String extraAddress;

    @Builder
    public MemberAddress(String streetAddress, String mainAddress, String memberPostNum, String detailAddress, String extraAddress) {
        this.streetAddress = streetAddress;
        this.mainAddress = mainAddress;
        this.memberPostNum = memberPostNum;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
    }
}
