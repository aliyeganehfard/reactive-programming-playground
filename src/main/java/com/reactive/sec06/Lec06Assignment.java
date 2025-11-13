package com.reactive.sec06;

import com.reactive.common.Util;
import com.reactive.sec06.assignment.ExternalServiceClient06;
import com.reactive.sec06.assignment.InventoryService;
import com.reactive.sec06.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient06();

        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(30);
    }
}
