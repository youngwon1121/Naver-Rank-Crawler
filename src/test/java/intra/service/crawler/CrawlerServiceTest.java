package intra.service.crawler;

import intra.service.domain.Rank;
import intra.service.domain.Target;
import intra.service.domain.Type;
import intra.service.repository.RankRepository;
import intra.service.repository.TargetRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CrawlerServiceTest {

    @Autowired
    private TargetRepository targetRepository;
    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private RankRepository rankRepository;
    List<Target> targets = new ArrayList<>();

    @BeforeEach
    @Transactional
    void setTargets () throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/target"));
        String l;
        while ((l=bufferedReader.readLine()) != null) {
            String[] s = l.split(" ");
            Target target = new Target(s[0], s[1], Type.VIEW);
            targets.add(target);
            targetRepository.save(target);
        }
        targets = targets.subList(0,11);
    }

    @Test
    @DisplayName("서비스 확인")
    @Transactional
    void test() {
        //when
        crawlerService.getRankAndSaveBatch(targets);

        //then
        List<Rank> ranks = rankRepository.findAll();
        Assertions.assertThat(ranks.size()).isEqualTo(targets.size());
    }
}
