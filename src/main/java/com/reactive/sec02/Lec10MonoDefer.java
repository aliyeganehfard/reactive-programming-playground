package com.reactive.sec02;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoDefer {

    private static final Logger log = LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {

        createPublisher()
                .subscribe(Util.subscriber());

        System.out.println("------------");

        Mono.defer(Lec10MonoDefer::createPublisher)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher(){
        log.info("creating publisher");
        var numbers = List.of(1,2,3);
        Util.sleepSeconds(3);
        return Mono.fromSupplier(() -> sum(numbers));
    }

    // time-consuming business logic
    private static int sum(List<Integer> numbers) {
        log.info("finding sum of {} numbers", numbers);
        Util.sleepSeconds(3);
        return numbers.stream().reduce(0, Integer::sum);
    }
}
