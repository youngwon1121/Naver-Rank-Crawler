package intra.service.repository;

import intra.service.domain.Rank;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RankRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Rank rank) {
        em.persist(rank);
    }

    public List<Rank> findAll() {
        return em.createQuery("select r from Rank r", Rank.class).getResultList();
    }
}
