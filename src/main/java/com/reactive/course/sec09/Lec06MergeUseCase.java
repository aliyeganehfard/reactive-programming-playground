package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import com.reactive.course.sec09.helper.Kayak;

import java.time.Duration;

public class Lec06MergeUseCase {
    public static void main(String[] args) {

        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(3));
    }
}
