package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DetailPageDto {
    private String id;
    private String campName;
    private List<String> prices;
    private String image;
    private String detailIntro;
    private String simpleIntro;
    private String campAddress;
    private Long campTotalAvg;
    private int like;
    private int totalLike;
    private List<LocalDate> campReservationDay;
    private List<CampCommentDto> campContents;

    @Builder
    public DetailPageDto(String id, String campName, List<String> prices, String image, String detailIntro, String simpleIntro, String campAddress, Long campTotalAvg, List<LocalDate> campReservationDay, List<CampCommentDto> campContents, int totalLike,int like) {
        this.id = id;
        this.campName = campName;
        this.prices = prices;
        this.image = image;
        this.detailIntro = detailIntro;
        this.simpleIntro = simpleIntro;
        this.campAddress = campAddress;
        this.campTotalAvg = campTotalAvg;
        this.campReservationDay = campReservationDay;
        this.campContents = campContents;
        this.totalLike=totalLike;
        this.like=like;
    }
}
