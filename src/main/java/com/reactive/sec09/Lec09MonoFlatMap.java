package com.reactive.sec09;

import com.reactive.common.Util;
import com.reactive.sec09.applications.PaymentService;
import com.reactive.sec09.applications.UserService;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
    }
}
