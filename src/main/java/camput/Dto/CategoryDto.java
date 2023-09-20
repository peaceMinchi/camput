package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;


@Getter
@Setter
public class CategoryDto {

    private String star;

    private List<String> location;

    private String animalCheck;

    private List<String> campKinds;

    @Builder
    public CategoryDto(String star, List<String> location, String animalCheck, List<String> campKinds) {
        this.star= star;
        this.location = location;
        this.animalCheck = animalCheck;
        this.campKinds = campKinds;
    }
}
