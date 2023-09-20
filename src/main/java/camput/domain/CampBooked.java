package camput.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CampBooked {

    @Id
    @GeneratedValue
    @Column(name = "campbooked_id")
    private Long id;
    private int campPrice;
    private LocalDateTime campBookedDay;
    private LocalDate cStartDay;
    private LocalDate cEndDay;
    @ManyToOne(fetch = FetchType.LAZY)
    private Camput camput;
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberBooked memberBooked;
    @Builder
    public CampBooked(int campPrice, LocalDateTime campBookedDay, LocalDate cStartDay, LocalDate cEndDay, Camput camput, MemberBooked memberBooked) {
        this.campPrice = campPrice;
        this.campBookedDay = campBookedDay;
        this.cStartDay = cStartDay;
        this.cEndDay = cEndDay;
        this.camput = camput;
        this.memberBooked = memberBooked;
    }

    public CampBooked addMemberBooked(MemberBooked memberBooked) {
        this.memberBooked=memberBooked;
        return this;
    }
}
