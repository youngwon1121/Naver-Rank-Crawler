package intra.service.DTO;

import intra.service.domain.Target;
import intra.service.domain.Type;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TargetRequestDTO {
    @NotEmpty
    private String keyword;
    @NotEmpty
    private String  uri;

    @NotNull
    private Type type;

    public Target toEntity() {
        return new Target(keyword, uri, type);
    }
}
