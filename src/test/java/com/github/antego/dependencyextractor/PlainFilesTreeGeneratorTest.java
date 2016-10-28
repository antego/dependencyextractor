package com.github.antego.dependencyextractor;

import jdk.nashorn.internal.objects.ArrayBufferView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static com.github.antego.dependencyextractor.LibNodeType.CLASS;
import static org.junit.Assert.assertEquals;

public class PlainFilesTreeGeneratorTest {
    private Path tempDir;

    @Before
    public void createTempFilesystem() throws IOException {
        tempDir = Files.createTempDirectory("treeConstructorTest");
    }

    @After
    public void deleteTempDir() throws IOException {
        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void shouldReturnNodeForFile() throws IOException {
        Path tempFile = Files.createTempFile(tempDir, "treeConstructorTestFile", null);

        TreeConstructor treeConstructor = new TreeConstructor();
        assertEquals("Wrong traverse result", CLASS,
                treeConstructor.construct(tempDir).getChildren().iterator().next().getType());

    }
}
