package com.reactive.sec03.client;

import com.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServiceClientFlux extends AbstractHttpClient {

    public Flux<String> getNames(){
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getPriceChanges(){
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
