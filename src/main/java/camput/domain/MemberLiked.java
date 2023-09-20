package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberLiked {

    @Id
    @GeneratedValue
    @Column(name = "memberLiked_id")
    private Long id;
    private String likedCampName;
    private String likedCampAddress;
    private String likedCampImageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @Builder
    public MemberLiked(String likedCampName, String likedCampAddress, String likedCampImageUrl, Member member) {
        this.likedCampName = likedCampName;
        this.likedCampAddress = likedCampAddress;
        this.likedCampImageUrl = likedCampImageUrl;
        this.member = member;
    }
}
