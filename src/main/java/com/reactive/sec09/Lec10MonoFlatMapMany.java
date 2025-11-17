package com.reactive.sec09;

import com.reactive.common.Util;
import com.reactive.sec09.applications.OrderService;
import com.reactive.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
