package intra.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Target {
    public Target(String keyword, String uri, Type type) {
        this.keyword = keyword;
        this.uri = uri;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyword;
    @Column(length = 1000)
    private String uri;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @OneToMany(mappedBy = "target")
    private List<Rank> ranks = new ArrayList<>();
}
