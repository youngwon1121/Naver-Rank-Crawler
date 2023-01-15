package intra.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
public class Response <T>{
    private T data;
    private HttpStatus status;

    public ResponseEntity<Response<T>> toResponse() {
        return ResponseEntity.status(status)
                .body(this);
    }
}
