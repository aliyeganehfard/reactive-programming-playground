package com.reactive.sec02;

import com.reactive.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        var mono = Mono.just("hello");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        var subscription = subscriber.getSubscription();
        subscription.request(Long.MAX_VALUE);

        subscription.cancel();
    }
}
