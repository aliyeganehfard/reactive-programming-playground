package com.reactive.sec01;

import com.reactive.sec01.publisher.PublisherImpl;
import com.reactive.sec01.subscriber.SubscriberImpl;

public class Demo {
    public static void main(String[] args) throws InterruptedException {

//        demo2();

//        demo3();

        demo4();
    }

    private static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        var subscription = subscriber.getSubscription();

        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        var subscription = subscriber.getSubscription();

        subscription.request(3);

        Thread.sleep(2000);
        subscription.cancel();

        subscription.request(3);

    }

    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        var subscription = subscriber.getSubscription();

        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(11);

        Thread.sleep(2000);
        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);

        Thread.sleep(2000);
        subscription.request(3);

    }
}
