package com.reactive.sec05;

import com.reactive.common.Util;
import com.reactive.sec05.assignment.ExternalServiceClient05;

public class Assignment {
    public static void main(String[] args) {

        var client = new ExternalServiceClient05();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(5);
    }
}
