package com.reactive.sec07;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {

    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(3).runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
                .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static int process(int i){
        log.info("time consuming task {}", i);
        Util.sleepSeconds(1);
        return i * 2;
    }
}
