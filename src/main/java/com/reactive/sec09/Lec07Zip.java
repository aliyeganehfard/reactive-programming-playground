package com.reactive.sec09;

import com.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    private final static Logger log = LoggerFactory.getLogger(Lec07Zip.class);

    record Car(String body, String engine, String tire){}

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngin(), getTires())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Flux<String> getBody(){
        return Flux.range(1,5)
                .map(i -> "body-"+i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngin(){
        return Flux.range(1,3)
                .map(i -> "engine-"+i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires(){
        return Flux.range(1,3)
                .map(i -> "tires-"+i)
                .delayElements(Duration.ofMillis(75));
    }

    private static Flux<Integer> producer4(){
        return Flux.range(1,10);
    }


}
