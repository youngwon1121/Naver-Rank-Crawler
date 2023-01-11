package intra.service.repository;

import intra.service.domain.Rank;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RankRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Rank rank) {
        em.persist(rank);
    }
}
