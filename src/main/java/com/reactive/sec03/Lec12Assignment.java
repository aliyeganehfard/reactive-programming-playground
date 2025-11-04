package com.reactive.sec03;

import com.reactive.common.Util;
import com.reactive.sec03.assignment.StockPriceObserver;
import com.reactive.sec03.client.ExternalServiceClientFlux;


public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClientFlux();
        var subscriber = new StockPriceObserver();
        client.getPriceChanges().subscribe(subscriber);

        Util.sleepSeconds(22);
    }
}
