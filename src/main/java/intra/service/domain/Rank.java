package intra.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@NoArgsConstructor
@Getter
public class Rank {
    public Rank(Target target, int rank) {
        this.target = target;
        target.getRanks().add(this);
        this.rank = rank;
        this.createTime = LocalDateTime.now();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rank;
    @ManyToOne(fetch = FetchType.LAZY)
    private Target target;
    private LocalDateTime createTime;

}
