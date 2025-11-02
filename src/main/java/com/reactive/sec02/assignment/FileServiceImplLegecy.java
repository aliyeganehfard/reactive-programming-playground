package com.reactive.sec02.assignment;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileServiceImplLegecy implements FileService {

    public static void main(String[] args) {
        var fileService = new FileServiceImplLegecy();

        var writeMono = fileService.write("test", "world");
        System.out.println("starting write");
        writeMono.subscribe();

        System.out.println("----------------");

        var readMono = fileService.read("test");
        System.out.println("read mono");
        readMono.subscribe();


        System.out.println("----------------");
    }

    private static final String BASE_URI = "resources/sec02/%s.txt";

    @Override
    public Mono<String> read(String filename) {
        return Mono.fromCallable(() -> {
                    var path = Paths.get(String.format(BASE_URI, filename));
                    return Files.readString(path);
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> write(String filename, String content) {
        return Mono.fromRunnable(() -> {
            var path = Paths.get(String.format(BASE_URI, filename));
            try {
                Files.writeString(path, content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public Mono<Void> delete(String filename) {
        return Mono.fromRunnable(() -> {
            var path = Paths.get(String.format(BASE_URI, filename));
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
