package com.reactive.sec07;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04VirtualThread {

    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThread.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(()-> log.info("first1-{}", Thread.currentThread().getName()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(()-> log.info("first2"));

        Runnable runnable1 = () ->flux.subscribe(Util.subscriber("sub1"));

        new Thread(runnable1).start();

        Util.sleepSeconds(2);
    }
}
