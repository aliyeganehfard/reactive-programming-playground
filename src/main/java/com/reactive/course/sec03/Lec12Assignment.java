package com.reactive.course.sec03;

import com.reactive.course.common.Util;
import com.reactive.course.sec03.assignment.StockPriceObserver;
import com.reactive.course.sec03.client.ExternalServiceClientFlux;


public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClientFlux();
        var subscriber = new StockPriceObserver();
        client.getPriceChanges().subscribe(subscriber);

        Util.sleepSeconds(22);
    }
}
