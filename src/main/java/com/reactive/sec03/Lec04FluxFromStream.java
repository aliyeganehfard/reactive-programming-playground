package com.reactive.sec03;

import com.reactive.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4);

        var fluxWithSupplier = Flux.fromStream(list::stream);

        fluxWithSupplier.subscribe(Util.subscriber("sub1"));
        fluxWithSupplier.subscribe(Util.subscriber("sub2"));

        var stream = list.stream();

        var flux = Flux.fromStream(stream);
        flux.subscribe(Util.subscriber("sub3"));
        flux.subscribe(Util.subscriber("sub4"));


    }
}
