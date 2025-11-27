package com.reactive.course.sec13;

import com.reactive.course.common.Util;
import com.reactive.course.sec13.client.ExternalServiceClient13;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {

    public static void main(String[] args) {

        var client = new ExternalServiceClient13();

        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "sam"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }


        Util.sleepSeconds(11);
    }
}
