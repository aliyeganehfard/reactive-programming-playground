package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import com.reactive.course.sec09.applications.PaymentService;
import com.reactive.course.sec09.applications.UserService;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
    }
}
