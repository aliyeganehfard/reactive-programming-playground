package com.reactive.sec03;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09FluxInterval {
    private static final Logger log = LoggerFactory.getLogger(Lec09FluxInterval.class);

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        log.info("hello");
        Util.sleepSeconds(5);
    }
}
