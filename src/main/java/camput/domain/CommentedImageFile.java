package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentedImageFile {

    @Id
    @GeneratedValue
    @Column(name = "commentedimagefile_id")
    private Long id;
    private String saveImageUrl;
    private String imageOriginalUrl;
    private String imageFilename;
    private LocalDateTime imageDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Commented commented;

    @Builder
    public CommentedImageFile(String saveImageUrl, String imageOriginalUrl, String imageFilename, LocalDateTime imageDate, Commented commented) {
        this.saveImageUrl = saveImageUrl;
        this.imageOriginalUrl = imageOriginalUrl;
        this.imageFilename = imageFilename;
        this.imageDate = imageDate;
        this.commented = commented;
    }
}
