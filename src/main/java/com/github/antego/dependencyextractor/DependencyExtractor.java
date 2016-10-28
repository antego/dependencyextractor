package com.github.antego.dependencyextractor;


import java.nio.file.Path;
import java.nio.file.Paths;

public class DependencyExtractor {
    public static void main(String[] args) {
        if (args.length < 3) throw new RuntimeException("Program uses 3 arguments");

        Path sourcesPath = Paths.get(args[0]);
        Path libraryPath = Paths.get(args[1]);
        Path targetPath = Paths.get(args[2]);


    }
}
