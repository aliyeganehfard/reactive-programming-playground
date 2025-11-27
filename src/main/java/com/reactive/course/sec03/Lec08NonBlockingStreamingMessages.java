package com.reactive.course.sec03;

import com.reactive.course.common.Util;
import com.reactive.course.sec03.client.ExternalServiceClientFlux;

public class Lec08NonBlockingStreamingMessages {
    public static void main(String[] args) {

        var client = new ExternalServiceClientFlux();
        client.getNames()
                .subscribe(Util.subscriber("sub1"));

        client.getNames()
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(6);
    }
}
