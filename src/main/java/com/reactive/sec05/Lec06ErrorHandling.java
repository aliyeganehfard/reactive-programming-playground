package com.reactive.sec05;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((ex, obj) -> log.error("===> {}", obj, ex))
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturn() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorReturn(IllegalAccessError.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume(){
        Mono.just(5)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorResume(ArithmeticException.class, ex -> fallback())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-3)// if fall backs failed
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete(){
        Mono.error(new RuntimeException("oops"))
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
    }
}
