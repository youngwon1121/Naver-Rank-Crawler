package intra.service.repository;

import intra.service.domain.Target;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TargetRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Target target) {
        em.persist(target);
    }

    public Target findOne(Long id) {
        return em.find(Target.class, id);
    }
    public List<Target> findAll() {
        return em.createQuery("select t from Target t", Target.class)
                .getResultList();
    }
}
