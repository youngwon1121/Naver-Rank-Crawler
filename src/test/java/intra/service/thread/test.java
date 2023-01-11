package intra.service.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {
    @Test
    void completableFuture1() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10000;
        }, executorService).thenAccept((s) -> {
            System.out.println(s);
        });

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5000;
        }, executorService).thenAccept((s) -> {
            System.out.println(s);
        });

        executorService.shutdown();
    }
    @Test
    void completableFuture_with_executor() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10000;
        }, executorService).thenAccept((s) -> {
            System.out.println(s);
        });

        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5000;
        }, executorService).thenAccept((s) -> {
            System.out.println(s);
        });

        executorService.shutdown();
    }
}
