package com.reactive.projects.p01MonoLab;

import com.reactive.course.common.Util;
import com.reactive.projects.p01MonoLab.model.Profile;
import com.reactive.projects.p01MonoLab.repo.InMemoryProfileRepo;
import com.reactive.projects.p01MonoLab.util.FileSimulator;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        var p1 = new Profile(
                "1",
                Util.faker().name().name(),
                Util.faker().internet().emailAddress(),
                Util.faker().address().fullAddress(),
                Util.faker().random().nextInt(1, 100)
        );

        var repo = new InMemoryProfileRepo();
        repo.save(p1);
        System.out.println(repo.findById("1"));

        var fileSimulator = new FileSimulator();
        fileSimulator.writeFile("t1", "hello");

        try {
            var content = fileSimulator.readFile("t1");
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
