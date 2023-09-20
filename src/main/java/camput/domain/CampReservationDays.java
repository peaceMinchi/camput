package camput.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampReservationDays {
    @Id@GeneratedValue
    @Column(name = "campReservationDays_id")
    private Long id;
    private String memberName;
    private LocalDate reservationDays;
    private int count;
    private String campName;

    @Builder
    public CampReservationDays(String memberName,String campName ,LocalDate reservationDays, int count) {
        this.memberName = memberName;
        this.reservationDays = reservationDays;
        this.count = count;
        this.campName = campName;
    }

    public CampReservationDays addCount(){
        this.count+=1;
        return this;
    }
    public CampReservationDays minusCount(){
        this.count-=1;
        return this;
    }
}
