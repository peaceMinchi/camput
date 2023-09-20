package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookedDayDto {

    public int year;
    public int month;
    public int day;
    public int totalBookedCount;
    public String cantReservation;

    @Builder
    public BookedDayDto(int month, int day, int totalBookedCount,String cantReservation, int year) {
        this.year=year;
        this.month = month;
        this.day = day;
        this.totalBookedCount = totalBookedCount;
        this.cantReservation=cantReservation;
    }
}
