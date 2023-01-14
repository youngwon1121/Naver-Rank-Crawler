package intra.service.repository;

import intra.service.DTO.TargetDTO;
import intra.service.domain.Rank;
import intra.service.domain.Target;
import intra.service.domain.Type;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TargetRepositoryTest {
    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private EntityManager em;
    @Test
    void save() {
        //given
        Target target = new Target("java", "https://blog.naver.com/poohhoo/", Type.VIEW);

        //when
        targetRepository.save(target);

        //then
        Assertions.assertThat(target).isSameAs(targetRepository.findOne(1L));
    }

    @Test
    void findAll() {
        //given
        Target target1 = new Target("java", "https://blog.naver.com/poohhoo/", Type.VIEW);
        Rank rank1 = new Rank(target1, 1);

        Target target2 = new Target("java2", "https://blog.naver.com/poohhoo/", Type.VIEW);
        Rank rank2 = new Rank(target2, 2);
        em.persist(target1);
        em.persist(target2);
        em.persist(rank2);
        em.persist(rank1);
        em.flush();

        Rank rank3 = new Rank(target1, 3);
        Rank rank4 = new Rank(target2, 4);
        em.persist(rank3);
        em.persist(rank4);
        em.flush();

        //when
        List<TargetDTO> allWithLatestRank = targetRepository.findAllWithLatestRank();

        //then
        Assertions.assertThat(allWithLatestRank.get(0).getRank()).isEqualTo(3);
        Assertions.assertThat(allWithLatestRank.get(1).getRank()).isEqualTo(4);
    }

}