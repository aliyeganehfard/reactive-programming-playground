package com.reactive.sec02;

import com.reactive.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(1).subscribe(Util.subscriber("1"));

        getUsername(2).subscribe(Util.subscriber("2"));

        getUsername(3).subscribe(Util.subscriber("3"));


        System.out.println("----------------------------------------");


        getUsername(3).subscribe(System.out::println);
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
}
