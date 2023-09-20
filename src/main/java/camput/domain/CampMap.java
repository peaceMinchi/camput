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
public class CampMap {

    @Id
    @GeneratedValue
    @Column(name = "campmap_id")
    private Long id;
    private String mapX;
    private String mapY;

    @Builder
    public CampMap(String mapX, String mapY) {
        this.mapX = mapX;
        this.mapY = mapY;
    }
}
