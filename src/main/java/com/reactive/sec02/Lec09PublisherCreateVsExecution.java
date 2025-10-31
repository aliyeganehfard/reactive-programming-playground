package com.reactive.sec02;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec09PublisherCreateVsExecution {

    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {

        getName().subscribe(Util.subscriber());
    }

    private static Mono<String> getName() {
        return Mono.fromSupplier(()->{
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }
}
