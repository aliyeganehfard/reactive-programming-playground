package com.reactive.course.sec13.client;

import com.reactive.course.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient13 extends AbstractHttpClient {

    public Mono<String> getBook() {
        return this.httpClient.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls())
                .contextWrite(UserService.userCategoryContext())
                .next();
    }

}
