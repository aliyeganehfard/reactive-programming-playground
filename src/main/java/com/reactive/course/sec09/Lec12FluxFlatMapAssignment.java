package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import com.reactive.course.sec09.client.ExternalServiceClient09;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient09();

        Flux.range(1,10)
                .flatMap(client::getProduct, 3)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(130);
    }
}
