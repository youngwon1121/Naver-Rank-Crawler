package intra.service.crawler;

import intra.service.domain.Target;
import intra.service.domain.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CrawlerTest {
    List<Target> targets = new ArrayList<>();
    @BeforeEach
    void setTargets () throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/test/resources/target"));
        String l;
        while ((l=bufferedReader.readLine()) != null) {
            String[] s = l.split(" ");
            Target target = new Target(s[0], s[1], Type.VIEW);
            targets.add(target);
        }
    }

    @Test
    @DisplayName("동기")
    void test_sync() {
        long beforeTime = System.currentTimeMillis();
        for (Target target : targets) {
            Crawler crawler = new ViewCrawler(target.getKeyword(), target.getUri());
            System.out.println(crawler.start());
        }
        long afterTime = System.currentTimeMillis();
        System.out.println("시간 : " +(afterTime - beforeTime)/1000);
    }

    @Test
    @DisplayName("비동기")
    void test_async() {
        long beforeTime = System.currentTimeMillis();
        List<CompletableFuture> futures = new ArrayList<>();
        for (Target target : targets) {
            CompletableFuture<Void> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                Crawler crawler = new ViewCrawler(target.getKeyword(), target.getUri());
//                System.out.println(Thread.currentThread());
                return crawler.start();
            }).thenAccept(s -> {
                System.out.println(s);
            });
            futures.add(integerCompletableFuture);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        long afterTime = System.currentTimeMillis();
        System.out.println("시간 : " +(afterTime - beforeTime)/1000);
    }

    @Test
    @DisplayName("비동기 스레드풀")
    void test_async2() {
        long beforeTime = System.currentTimeMillis();
        List<CompletableFuture> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (Target target : targets) {
            CompletableFuture<Void> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
                Crawler crawler = new ViewCrawler(target.getKeyword(), target.getUri());
//                System.out.println(Thread.currentThread());
                return crawler.start();
            }, executorService).thenAccept(s -> {
                System.out.println(s);
            });
            futures.add(integerCompletableFuture);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        long afterTime = System.currentTimeMillis();
        System.out.println("시간 : " +(afterTime - beforeTime)/1000);
    }

}