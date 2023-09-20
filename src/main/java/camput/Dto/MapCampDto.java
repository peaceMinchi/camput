package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapCampDto {
    private Long id;
    private String name;
    private String doNum;
    private String animalCheck;
    private Long totalStars;
    private String caraban;
    private String gramping;
    private String imageUrl;
    private String lineIntro;
    private String homepageUrl;
    private String mapX;
    private String mapY;
    private String simpleAddress;
    @Builder

    public MapCampDto(Long id, String name, String doNum, String animalCheck, Long totalStars, String caraban, String gramping, String imageUrl, String lineIntro, String homepageUrl, String mapX, String mapY, String simpleAddress) {
        this.id = id;
        this.name = name;
        this.doNum = doNum;
        this.animalCheck = animalCheck;
        this.totalStars = totalStars;
        this.caraban = caraban;
        this.gramping = gramping;
        this.imageUrl = imageUrl;
        this.lineIntro = lineIntro;
        this.homepageUrl = homepageUrl;
        this.mapX = mapX;
        this.mapY = mapY;
        this.simpleAddress = simpleAddress;
    }
}
