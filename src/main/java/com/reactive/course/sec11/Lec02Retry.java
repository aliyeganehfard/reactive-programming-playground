package com.reactive.course.sec11;

import com.reactive.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {

    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo2();

        Util.sleep(Duration.ofSeconds(10));
    }

    private static void demo1() {
        getCountryName()
                .retry()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(4, Duration.ofSeconds(1))
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .doBeforeRetry(rs -> log.info("retying {}", rs.totalRetries()))
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 3) {
                        throw new RuntimeException("oops");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(err -> log.error("Error: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("Subscribed: {}", s));
    }
}
