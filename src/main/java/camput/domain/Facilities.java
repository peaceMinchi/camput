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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Facilities {

    @Id
    @GeneratedValue
    @Column(name = "facilities_id")
    private Long id;
    private String waterPlace;
    private String brazer;
    private String subStructure;
    private String extinguisher;

    @Builder
    public Facilities(String waterPlace, String brazer, String subStructure, String extinguisher) {
        this.waterPlace = waterPlace;
        this.brazer = brazer;
        this.subStructure = subStructure;
        this.extinguisher = extinguisher;
    }
}
