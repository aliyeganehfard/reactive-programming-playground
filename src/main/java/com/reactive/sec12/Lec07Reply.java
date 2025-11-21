package com.reactive.sec12;

import com.reactive.common.Util;
import reactor.core.publisher.Sinks;

public class Lec07Reply {

    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {

        var sink = Sinks.many().replay().limit(2);
//        var sink = Sinks.many().replay().all();

        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new message");
    }

}
