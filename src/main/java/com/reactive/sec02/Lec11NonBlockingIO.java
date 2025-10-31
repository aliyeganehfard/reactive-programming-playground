package com.reactive.sec02;

import com.reactive.common.Util;
import com.reactive.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        long nonBlockingStartInMillis = System.nanoTime();

        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        long nonBlockingFinishInMillis = System.nanoTime();

        long syncStartAsMillis = System.nanoTime();

        for (int i = 1; i <= 5; i++) {
            var name = client.getProductNameSyncBlocking(i);
            log.info("Product name: {}", name);
        }


        long syncFinishDurationInMillis = System.nanoTime();

        double syncDuration = (syncFinishDurationInMillis - syncStartAsMillis) / 1_000_000_000.0;
        double nonBlockingDuration = (nonBlockingFinishInMillis - nonBlockingStartInMillis) / 1_000_000_000.0;

        System.out.println("sync blocking duration is " + syncDuration);
        System.out.println("nonBlocking duration is " + nonBlockingDuration);
        Util.sleepSeconds(2);
    }
}
