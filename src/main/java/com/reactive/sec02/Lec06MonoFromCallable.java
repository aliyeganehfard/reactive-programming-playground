package com.reactive.sec02;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {

        var numbers = List.of(1,2,3);

        // is support checked exception
        Mono.fromCallable(() -> sum(numbers))
                .subscribe(Util.subscriber("supplier"));
    }

    private static int sum(List<Integer> numbers) throws Exception {
        log.info("finding sum of {} numbers", numbers);
        return numbers.stream().reduce(0, Integer::sum);
    }
}
