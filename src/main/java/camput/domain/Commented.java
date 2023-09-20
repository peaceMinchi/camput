package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Commented {

    @Id
    @GeneratedValue
    @Column(name = "commented_id")
    private Long id;
    private String commentedContent;
    private String commentedMemberName;
    private int stars;
    private LocalDateTime commentedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Camput camput;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<CommentedImageFile> commentedImageFiles= new ArrayList<>();

    @Builder
    public Commented(String commentedContent, String commentedMemberName, int stars, LocalDateTime commentedDate, Member member, Camput camput, List<CommentedImageFile> commentedImageFiles) {
        this.commentedContent = commentedContent;
        this.commentedMemberName = commentedMemberName;
        this.stars = stars;
        this.commentedDate = LocalDateTime.now();
        this.member = member;
        this.camput = camput;
        this.commentedImageFiles = commentedImageFiles;
    }

    public void commentedUpdate(long id, String commentedContent, Member member, Camput camput){
        this.id = id;
        this.commentedContent = commentedContent;
        this.member = member;
        this.camput = camput;
    }
}
