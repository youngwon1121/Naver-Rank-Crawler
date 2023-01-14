package intra.service.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import intra.service.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
public class TargetDTO {
    private Long id;
    private String keyword;
    private String  uri;
    private String type;
    private int rank;
    private LocalDateTime createTime;
}

