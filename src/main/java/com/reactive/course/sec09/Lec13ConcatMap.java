package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import com.reactive.course.sec09.client.ExternalServiceClient09;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {
    public static void main(String[] args) {
        var client = new ExternalServiceClient09();

        Flux.range(1,10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(13);
    }

}
