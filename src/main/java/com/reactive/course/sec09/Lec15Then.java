package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {

    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {

        var records = List.of("a", "b", "c", "d", "e", "f", "g", "h");
        saveRecords(records)
                .then(sendNotification(records))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved record: " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotification(List<String> records){
        return Mono.fromRunnable(() -> log.info("Sending notification {} records", records.size()));
    }
}
