package com.github.antego.dependencyextractor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import static com.github.antego.dependencyextractor.LibNodeType.CLASS;
import static com.github.antego.dependencyextractor.LibNodeType.JAR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlainFilesTreeGeneratorTest {
    TreeConstructor treeConstructor = new TreeConstructor();
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
    public void shouldReturnNodeForClassFile() {
        Path path = Paths.get("TreeConstructorTestClass.class");

        assertEquals("Wrong traverse result", CLASS,
                treeConstructor.createRootNode(path).getType());
    }

    @Test
    public void shouldReturnNodeForJarFile() {
        Path path = Paths.get("TreeConstructorTestJar.jar");

        assertEquals("Wrong traverse result", JAR,
                treeConstructor.createRootNode(path).getType());
    }

    @Test
    public void shouldReturnNodeForDir() {
        Path path = mock(Path.class);
        File file = mock(File.class);
        when(file.isDirectory()).thenReturn(true);
        when(path.toFile()).thenReturn(file);
        when(path.getFileName()).thenReturn(mock(Path.class));

        assertEquals("Wrong traverse result", LibNodeType.DIRECTORY,
                treeConstructor.createRootNode(path).getType());
    }

    @Test(expected = RuntimeException.class)
    public void shouldRaiseException() {
        Path path = Paths.get("TreeConstructorTestJar");

        assertEquals("Wrong traverse result", LibNodeType.DIRECTORY,
                treeConstructor.createRootNode(path).getType());
    }
}
