package com.reactive.sec07;

import com.reactive.common.Util;
import com.reactive.sec07.client.ExternalServiceClient07;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06EventLoopIssueFix {

    private final static Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient07();

        log.info("start");

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }


        Util.sleepSeconds(20);
    }

    private static String process(String input) {
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
