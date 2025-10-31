package com.reactive.sec02;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {

        getProductName(1).subscribe(Util.subscriber());

        System.out.println("--------------------------");

        getProductName(2).subscribe(Util.subscriber());

        System.out.println("--------------------------");

        getProductName(3).subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId) {
        if(productId == 1){
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }

        if( productId == 2){
            return Mono.empty();
        }

        return Mono.fromRunnable(()-> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId) {
        log.info("notifying business on unavailable product {}", productId);
    }
}
