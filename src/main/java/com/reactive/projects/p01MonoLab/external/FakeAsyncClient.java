package com.reactive.projects.p01MonoLab.external;

import com.reactive.course.common.Util;

import java.util.concurrent.CompletableFuture;

public class FakeAsyncClient {
    public CompletableFuture<Integer> getExternalScore(String profileId) {
        return CompletableFuture.supplyAsync(() -> {
            Util.sleepSeconds(1);
            var score = Util.faker().random().nextInt(1, 100);
            if (score < 40) {
                throw new RuntimeException("score is less than 40");
            }
            return score;
        });
    }
}
