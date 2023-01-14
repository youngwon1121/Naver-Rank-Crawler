package intra.service.service;

import intra.service.domain.Target;
import intra.service.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TargetService {
    private final TargetRepository targetRepository;

    public TargetService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    public Long saveTarget(Target target){
        targetRepository.save(target);
        return target.getId();
    }
}
