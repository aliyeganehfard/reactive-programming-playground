package com.reactive.common;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);

    private String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} received: {}", name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} error: {}", name, String.valueOf(throwable));
    }

    @Override
    public void onComplete() {
        log.info("{} complete!", name);
    }
}
