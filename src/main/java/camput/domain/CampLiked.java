package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampLiked {

    @Id
    @GeneratedValue
    @Column(name = "campliked_id")
    private Long id;
    @ManyToOne(fetch =FetchType.LAZY)
    private MemberLiked memberLiked;
    @ManyToOne(fetch = FetchType.LAZY)
    private Camput camput;

    @Builder
    public CampLiked(MemberLiked memberLiked, Camput camput) {
        this.memberLiked = memberLiked;
        this.camput = camput;
    }

    public CampLiked addMemberLike(MemberLiked memberLiked){
        this.memberLiked=memberLiked;
        return this;
    }
}
