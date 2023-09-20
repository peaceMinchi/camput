package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MyPageCampDto {
    private String myPageCampName;
    private String myPageCampAddress;
    private LocalDateTime myPageCampDay;
    private LocalDate myPageCampStartDay;
    private LocalDate myPageCampEndDay;
    private String myPageCampImageUrl;

    @Builder
    public MyPageCampDto(String myPageCampName, String myPageCampAddress, LocalDateTime myPageCampDay, LocalDate myPageCampStartDay, LocalDate myPageCampEndDay, String myPageCampImageUrl) {
        this.myPageCampName = myPageCampName;
        this.myPageCampAddress = myPageCampAddress;
        this.myPageCampDay = myPageCampDay;
        this.myPageCampStartDay = myPageCampStartDay;
        this.myPageCampEndDay = myPageCampEndDay;
        this.myPageCampImageUrl = myPageCampImageUrl;
    }
}