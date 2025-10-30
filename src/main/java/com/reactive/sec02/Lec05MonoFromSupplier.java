package com.reactive.sec02;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {

        var numbers = List.of(1,2,3);
        var sum = sum(numbers);

        Mono.just(sum)
                .subscribe(Util.subscriber("just"));

        // if we need subscription send with delay
        Mono.fromSupplier(() -> sum)
                .subscribe(Util.subscriber("supplier"));
    }

    private static int sum(List<Integer> numbers) {
        log.info("finding sum of {} numbers", numbers);
        return numbers.stream().reduce(0, Integer::sum);
    }
}
