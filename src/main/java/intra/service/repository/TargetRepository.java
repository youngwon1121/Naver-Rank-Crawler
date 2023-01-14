package intra.service.repository;

import intra.service.DTO.TargetDTO;
import intra.service.domain.Target;
import intra.service.domain.Type;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TargetDTO> findAllWithLatestRank() {
        String sql = "select t.id, t.keyword, t.uri, t.type, r2.rank, r2.create_time  from " +
                "target t " +
                "left join (select target_id, max(create_time) create_time from rank group by target_id) r1 on t.id = r1.target_id " +
                "left join rank r2 on r1.target_id = r2.target_id and r1.create_time = r2.create_time order by t.id asc";
        List<Object[]> resultList = em.createNativeQuery(sql).getResultList();
        return resultList.stream().map(t -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            return new TargetDTO(
                    t[0]==null ? null : ((BigInteger) t[0]).longValue(),
                    (String) t[1],
                    (String) t[2],
                    (String) t[3],
                    t[4]==null ? -1 : (Integer) t[4],
                    t[5]==null ? null : ((Timestamp)t[5]).toLocalDateTime()
            );
        }).collect(Collectors.toList());
    }
}
