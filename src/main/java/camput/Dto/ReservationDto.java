package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class ReservationDto {
    @NotEmpty
    private LocalDate startDate;
    @NotEmpty
    private LocalDate endDate;
    @NotEmpty
    private int choicePrice;
    @NotEmpty
    private String memberLoginId;
    @NotEmpty
    private String campName;
    @NotEmpty
    private String memberName;

    @Builder
    public ReservationDto(LocalDate startDate, LocalDate endDate, int choicePrice, String memberLoginId, String campName, String memberName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.choicePrice = choicePrice;
        this.memberLoginId = memberLoginId;
        this.campName = campName;
        this.memberName = memberName;
    }
}
