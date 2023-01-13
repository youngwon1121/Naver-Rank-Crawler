package intra.service.crawler;

import intra.service.domain.Rank;
import intra.service.domain.Target;
import intra.service.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Service
@Transactional
@RequiredArgsConstructor
public class CrawlerService {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final RankRepository rankRepository;

    public void getRankAndSaveBatch(List<Target> targets) {
        List<CompletableFuture<Void>> targetFutures = new ArrayList<>();
        List<Rank> ranks = new ArrayList<>();
        for (Target target : targets) {
            CompletableFuture<Void> targetFuture = CompletableFuture.supplyAsync(() -> {
                Crawler crawler = CrawlerFactory.get(target);
                return crawler.start();
            }, executorService).thenAccept(r -> {
                Rank rank = new Rank(target, r);
                ranks.add(rank);
            });
            targetFutures.add(targetFuture);
        }
        CompletableFuture.allOf(targetFutures.toArray(new CompletableFuture[targetFutures.size()])).join();
        for (Rank rank : ranks) {
            rankRepository.save(rank);
        }
    }

    public void getRankAndSave(Target target) {
        Crawler crawler = CrawlerFactory.get(target);
        Rank rank = new Rank(target, crawler.start());
        rankRepository.save(rank);
    }
}
