package camput.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampImageFiles {
    @Id
    @GeneratedValue
    @Column(name = "campimagefile_id")
    private Long id;
    private String savedCampImageUrl;
    private String campOriginalUrl;
    private String campImageFileName;
    private LocalDateTime campImageDate;
    @Builder
    public CampImageFiles(String savedCampImageUrl, String campOriginalUrl, String campImageFileName, LocalDateTime campImageDate) {
        this.savedCampImageUrl = savedCampImageUrl;
        this.campOriginalUrl = campOriginalUrl;
        this.campImageFileName = campImageFileName;
        this.campImageDate = campImageDate;
    }
}
