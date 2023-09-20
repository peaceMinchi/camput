package camput.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LikeDto {

    private int totalLike;
    private int like;
    @Builder
    public LikeDto(int totalLike, int like) {
        this.totalLike = totalLike;
        this.like = like;
    }
}
