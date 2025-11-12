package com.reactive.sec05.assignment;

import com.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ExternalServiceClient05 extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
       var defaultPath = "/demo03/product/" + productId;
       var timeoutPath = "/demo03/timeout-fallback/product/" + productId;
       var emptyPath = "/demo03/empty-fallback/product/" + productId;
       return getProductName(defaultPath)
               .timeout(Duration.ofSeconds(2), getProductName(timeoutPath))
               .switchIfEmpty(getProductName(emptyPath));
    }

    private Mono<String> getProductName(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
