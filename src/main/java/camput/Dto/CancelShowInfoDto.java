package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CancelShowInfoDto {

    private String memberName;
    private String memberPhone;
    private String campName;
    private LocalDateTime bookedDay;
    private LocalDate startReservationDay;
    private LocalDate endReservationDay;
    private int price;
    private int point;
    private int afterMemberPoint;
    @Builder
    public CancelShowInfoDto(LocalDateTime bookedDay, String memberName, String memberPhone, String campName, LocalDate startReservationDay, LocalDate endReservationDay, int price, int point, int afterMemberPoint) {
        this.bookedDay = bookedDay;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.campName = campName;
        this.startReservationDay = startReservationDay;
        this.endReservationDay = endReservationDay;
        this.price = price;
        this.point = point;
        this.afterMemberPoint = afterMemberPoint;
    }
}
