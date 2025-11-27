package com.reactive.course.sec09;

import com.reactive.course.common.Util;
import com.reactive.course.sec09.applications.OrderService;
import com.reactive.course.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
