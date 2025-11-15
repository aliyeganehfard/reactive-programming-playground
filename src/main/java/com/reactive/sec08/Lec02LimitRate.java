package com.reactive.sec08;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02LimitRate {

    private static final Logger log = LoggerFactory.getLogger(Lec02LimitRate.class);

    public static void main(String[] args) {

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec02LimitRate::timeConsuming)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(50);
    }

    private static int timeConsuming(int i) {
        Util.sleepSeconds(1);
        log.info("consuming {}", i);
        return i;
    }
}
