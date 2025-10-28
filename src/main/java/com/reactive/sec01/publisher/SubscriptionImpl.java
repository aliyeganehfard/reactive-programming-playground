package com.reactive.sec01.publisher;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.logging.Logger;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = Logger.getLogger(SubscriptionImpl.class.getName());

    private Subscriber<? super String> subscriber;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {

    }

    @Override
    public void cancel() {

    }
}
