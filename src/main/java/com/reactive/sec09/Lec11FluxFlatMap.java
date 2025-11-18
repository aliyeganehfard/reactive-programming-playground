package com.reactive.sec09;

import com.reactive.common.Util;
import com.reactive.sec09.applications.OrderService;
import com.reactive.sec09.applications.UserService;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(user -> OrderService.getUserOrders(user.id()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
