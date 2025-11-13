package com.reactive.sec06.assignment;

import com.reactive.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

public class ExternalServiceClient06 extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient06.class);
    private Flux<Order> orderFlux;

    public Flux<Order> orderStream() {
        if (Objects.isNull(orderFlux)) {
            this.orderFlux = getOrderSteam();
        }
        return this.orderFlux;

    }

    private Flux<Order> getOrderSteam() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(o -> log.info("getOrderSteam: {}", o))
                .publish()
                .refCount(2);
    }

    private Order parse(String message) {
        var arr = message.split(":");
        return new Order(arr[1], Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
    }
}
