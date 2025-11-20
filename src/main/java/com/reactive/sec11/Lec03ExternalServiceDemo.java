package com.reactive.sec11;

import com.reactive.common.Util;
import com.reactive.sec11.client.ExternalServiceClient11;
import com.reactive.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {

        retry();

        Util.sleepSeconds(90);
    }

    private static void repeat(){
        var client = new ExternalServiceClient11();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry(){
        var client = new ExternalServiceClient11();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));
    }
}
