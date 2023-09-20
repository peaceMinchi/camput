package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.management.LockInfo;
import java.util.List;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String memberEmail;
    private String memberLoginId;
    private String memberPassword;
    private String nickName;
    private String memberName;
    private String memberPoint;
    private String phoneNumber;
    private String birthday;
    @OneToOne(fetch = FetchType.LAZY)
    private MemberAddress memberAddress;
    @Enumerated(EnumType.STRING)
    private MemberGender gender;
    @OneToMany
    private List<Commented> commented;

    @Builder
    public Member(String memberEmail, String memberLoginId, String memberPassword, String nickName, String memberName, String memberPoint, String phoneNumber, String birthday, MemberAddress memberAddress, MemberGender gender, List<Commented> commented, Long id) {
        this.memberEmail = memberEmail;
        this.memberLoginId = memberLoginId;
        this.memberPassword = memberPassword;
        this.nickName = nickName;
        this.memberName = memberName;
        this.memberPoint = memberPoint;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.memberAddress = memberAddress;
        this.gender = gender;
        this.commented = commented;
        this.id = id;
    }

    public void updateMember(String memberEmail,String nickName, String memberName,String phoneNumber, String birthday, MemberGender gender, MemberAddress save, String memberPassword){
        this.memberName = memberName;
        this.nickName = nickName;
        this.memberEmail = memberEmail;
        this.phoneNumber = phoneNumber;
        this.memberAddress = save;
        this.gender = gender;
        this.birthday = birthday;
        this.memberPassword = memberPassword;
//      this.memberPoint = memberPoint;

    }
    public Member plusPoint(int price){
        this.memberPoint=Integer.toString(Integer.parseInt(this.memberPoint)+price);
        return this;
    }

    public Member minusPoint(int price){
        this.memberPoint=Integer.toString(Integer.parseInt(this.memberPoint)-price);
        return this;
    }

    public void pwUpdateMember(String memberPassword){
        this.memberPassword = memberPassword;
    }
}
