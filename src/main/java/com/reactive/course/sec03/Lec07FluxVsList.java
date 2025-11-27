package com.reactive.course.sec03;

import com.reactive.course.sec01.subscriber.SubscriberImpl;
import com.reactive.course.sec03.helper.NameGenerator;

public class Lec07FluxVsList {
    public static void main(String[] args) {

        var list = NameGenerator.getNamesList(10);
        System.out.println(list);

        var subscriber = new SubscriberImpl();
        var flux = NameGenerator.getNamesFlux(10);
        flux.subscribe(subscriber);

        subscriber.getSubscription().request(4);
        subscriber.getSubscription().cancel();
    }
}
