package com.reactive.course.sec04;

import com.reactive.course.common.Util;
import com.reactive.course.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();

        fileReaderService.read(path)
                .subscribe(Util.subscriber());
    }
}
