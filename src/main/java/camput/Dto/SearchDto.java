package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private String searchText;
    @Builder
    public SearchDto(String searchText) {
        this.searchText = searchText;
    }
}
