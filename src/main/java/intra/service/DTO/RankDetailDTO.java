package intra.service.DTO;

import intra.service.domain.Rank;
import intra.service.domain.Target;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class RankDetailDTO {
    private Long id;
    private int rank;
    private LocalDateTime createTime;

    public RankDetailDTO(Rank rank) {
        this.id = rank.getId();
        this.rank = rank.getRank();
        this.createTime = rank.getCreateTime();
    }
}
