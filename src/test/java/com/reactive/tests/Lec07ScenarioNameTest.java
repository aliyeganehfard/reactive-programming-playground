package com.reactive.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {

    private Flux<Integer> getItems() {
        return Flux.range(1,3);
    }

    @Test
    public void scenarioNameTest1() {
        var options = StepVerifierOptions.create().scenarioName(" 1 to 3 items test");
        StepVerifier.create(getItems(), options)
                .expectNext(1)
                .as("first item should be 1")
                .expectNext(2,4)
                .as("then 2 and 4")
                .expectComplete()
                .verify();
    }
}
