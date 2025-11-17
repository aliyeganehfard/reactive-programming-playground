package com.reactive.sec09;

import com.reactive.common.Util;
import com.reactive.sec09.client.ExternalServiceClient09;

public class Lec08ZipAssignment {
    public static void main(String[] args) {

        var client = new ExternalServiceClient09();

        for (int i = 1; i <= 10 ; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(10);
    }
}
