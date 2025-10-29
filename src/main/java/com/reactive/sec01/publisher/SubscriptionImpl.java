package com.reactive.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubscriptionImpl implements Subscription {

    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

    private static final int MAX_ITEMS = 10;

    private final Faker faker;

    private final Subscriber<? super String> subscriber;

    private boolean isCancelled;

    private int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long request) {
        if (isCancelled) {return;}

        log.info("subscriber has request {} items", request);

        if(request > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("validation failed"));
            this.isCancelled = true;
            return;
        }

        for (int i = 0; i < request && count < MAX_ITEMS; i++) {
            count++;
            this.subscriber.onNext(faker.internet().emailAddress());
        }

        if(count == MAX_ITEMS) {
            log.info("no more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
    log.info("subscriber has been cancelled");
    this.isCancelled = true;
    }
}
