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
public class CampPrice {
    @Id@GeneratedValue
    @Column(name = "campPrice_id")
    private Long id;
    private int price;

    @Builder
    public CampPrice(int price) {
        this.price = price;
    }
}
