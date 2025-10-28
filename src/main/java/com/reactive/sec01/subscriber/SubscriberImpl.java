package com.reactive.sec01.subscriber;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.logging.Logger;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = Logger.getLogger(SubscriberImpl.class.getName());

    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info(email);
    }

    @Override
    public void onError(Throwable throwable) {
        log.severe("error:" + throwable);
    }

    @Override
    public void onComplete() {
        log.info("complete!");
    }
}
