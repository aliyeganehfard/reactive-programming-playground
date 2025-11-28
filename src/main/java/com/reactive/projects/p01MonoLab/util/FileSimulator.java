package com.reactive.projects.p01MonoLab.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSimulator {

    private static final String PATH = "src/main/resources/project/monoLab/%s.txt";

    public String readFile(String filename) throws IOException {
        var filePath  = Path.of(PATH.formatted(filename));
        return Files.readString(filePath);
    }
    public void writeFile(String filename, String content) {
        var filePath  = Path.of(PATH.formatted(filename));
        try {
            Files.writeString(filePath, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
