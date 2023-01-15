package intra.service.controller;

import intra.service.DTO.Response;
import intra.service.DTO.TargetDTO;
import intra.service.DTO.TargetDetailDTO;
import intra.service.DTO.TargetRequestDTO;
import intra.service.crawler.CrawlerService;
import intra.service.domain.Target;
import intra.service.repository.TargetRepository;
import intra.service.service.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TargetController {
    private final TargetRepository targetRepository;
    private final TargetService targetService;
    private final CrawlerService crawlerService;
    @GetMapping("/api/targets")
    public ResponseEntity<Response<List<TargetDTO>>> getTargetsAll() {
        List<TargetDTO> allWithLatestRank = targetRepository.findAllWithLatestRank();

        return Response.<List<TargetDTO>>builder()
                .data(allWithLatestRank)
                .status(HttpStatus.OK)
                .build()
                .toResponse();
    }

    @GetMapping("/api/targets/{id}")
    public ResponseEntity<Response<TargetDetailDTO>> getTarget(@PathVariable("id") Long id) {
        Target target = targetRepository.findOne(id);
        TargetDetailDTO targetDetailDTO = new TargetDetailDTO(target);

        return Response.<TargetDetailDTO>builder()
                .data(targetDetailDTO)
                .status(HttpStatus.OK)
                .build()
                .toResponse();
    }

    @PostMapping("/api/targets/")
    public ResponseEntity<Response<TargetDetailDTO>> saveTarget(@RequestBody @Validated TargetRequestDTO targetRequestDTO) {
        Long id = targetService.saveTarget(targetRequestDTO.toEntity());
        Target target = targetRepository.findOne(id);
        TargetDetailDTO targetDetailDTO = new TargetDetailDTO(target);
        return Response.<TargetDetailDTO>builder()
                .data(targetDetailDTO)
                .status(HttpStatus.CREATED)
                .build()
                .toResponse();
    }

    @PostMapping("/api/targets/{id}/rank")
    public void getRank(@PathVariable Long id) {
        Target target = targetRepository.findOne(id);
        crawlerService.getRankAndSave(target);
    }
}
