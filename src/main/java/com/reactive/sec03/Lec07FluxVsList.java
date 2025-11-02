package com.reactive.sec03;

import com.reactive.common.Util;
import com.reactive.sec01.subscriber.SubscriberImpl;
import com.reactive.sec03.helper.NameGenerator;

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
