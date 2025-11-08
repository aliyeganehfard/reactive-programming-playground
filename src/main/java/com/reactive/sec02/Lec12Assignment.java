package com.reactive.sec02;

import com.reactive.common.Util;
import com.reactive.sec02.assignment.FileServiceImpl;

public class Lec12Assignment {

    public static void main(String[] args) {
        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "Hello World")
                .subscribe(Util.subscriber());

        for (int i = 0; i < 10000; i++) {
            fileService.write("file.txt", String.format("hello %d", i))
                    .subscribe(Util.subscriber());
        }
//
//        fileService.read("file.txt")
//                .subscribe(Util.subscriber());
//
//        fileService.delete("file.txt")
//                .subscribe(Util.subscriber());
    }
}
