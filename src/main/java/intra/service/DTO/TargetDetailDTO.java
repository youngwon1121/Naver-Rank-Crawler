package intra.service.DTO;

import intra.service.domain.Target;
import intra.service.domain.Type;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TargetDetailDTO {
    private Long id;
    private String keyword;
    private String  uri;
    private Type type;
    private List<RankDetailDTO> ranks;
    public TargetDetailDTO (Target target) {
        id = target.getId();
        keyword = target.getKeyword();
        uri = target.getUri();
        type = target.getType();
        ranks = target.getRanks().stream().map(RankDetailDTO::new).collect(Collectors.toList());
    }
}
