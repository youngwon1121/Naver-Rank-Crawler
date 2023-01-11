package intra.service.repository;

import intra.service.domain.Target;
import intra.service.domain.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TargetRepositoryTest {
    @Autowired
    private TargetRepository targetRepository;
    @Test
    void save() {
        //given
        Target target = new Target("java", "https://blog.naver.com/poohhoo/", Type.VIEW);

        //when
        targetRepository.save(target);

        //then
        Assertions.assertThat(target).isSameAs(targetRepository.findOne(1L));
    }
}