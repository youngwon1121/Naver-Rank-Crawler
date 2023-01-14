package intra.service.controller;

import intra.service.DTO.TargetDTO;
import intra.service.repository.TargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TargetController {
    private final TargetRepository targetRepository;

    @GetMapping("/api/targets")
    public List<TargetDTO> getTargetsAll() {
        return targetRepository.findAllWithLatestRank();
    }
}
