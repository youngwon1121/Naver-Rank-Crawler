package intra.service.controller;

import intra.service.DTO.TargetDTO;
import intra.service.DTO.TargetDetailDTO;
import intra.service.DTO.TargetRequestDTO;
import intra.service.crawler.CrawlerService;
import intra.service.domain.Target;
import intra.service.repository.TargetRepository;
import intra.service.service.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TargetController {
    private final TargetRepository targetRepository;
    private final TargetService targetService;
    private final CrawlerService crawlerService;
    @GetMapping("/api/targets")
    public List<TargetDTO> getTargetsAll() {
        return targetRepository.findAllWithLatestRank();
    }

    @GetMapping("/api/targets/{id}")
    public ResponseEntity<TargetDetailDTO> getTarget(@PathVariable("id") Long id) {
        Target target = targetRepository.findOne(id);
        return ResponseEntity.ok(new TargetDetailDTO(target));
    }

    @PostMapping("/api/targets/")
    public Long saveTarget(@RequestBody @Validated TargetRequestDTO targetRequestDTO) {
        Target target = targetRequestDTO.toEntity();
        return targetService.saveTarget(target);
    }

    @PostMapping("/api/targets/{id}/rank")
    public void getRank(@PathVariable Long id) {
        Target target = targetRepository.findOne(id);
        crawlerService.getRankAndSave(target);
    }
}
