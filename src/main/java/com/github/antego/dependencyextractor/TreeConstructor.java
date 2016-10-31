package com.github.antego.dependencyextractor;


import java.nio.file.Path;

public class TreeConstructor {
    public LibNode createRootNode(Path rootPath) {
        LibNode node = new LibNode(null, getPathType(rootPath), rootPath.toString());
        return node;
    }

    private LibNodeType getPathType(Path path) {
        if (path.getFileName().toString().endsWith(".class")) {
            return LibNodeType.CLASS;
        } else if (path.getFileName().toString().endsWith(".jar")) {
            return LibNodeType.JAR;
        } else if (path.toFile().isDirectory()) {
            return LibNodeType.DIRECTORY;
        } else {
            throw new RuntimeException("Invalid file type");
        }
    }
}
