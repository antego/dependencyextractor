package com.github.antego.dependencyextractor;

import com.github.antego.dependencyextractor.ImportsExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class DependencyExtractorTest {
    ImportsExtractor ie = new ImportsExtractor();

    @Test
    public void testJavaFileParsing() {
        String javaFile = "import java.util.concurrent.*;\n";
        assertEquals("Wrong package", "java.util.concurrent.*", ie.extract(javaFile).iterator().next());
    }

    @Test
    public void testExtractManyImports() {
        String javaFile = "import java.util.concurrent.*;\n" +
                "import java.lang.Object;";

        assertEquals("Wrong number of imports", 2, ie.extract(javaFile).size());
        assertTrue("Wrong first import", ie.extract(javaFile).contains("java.util.concurrent.*"));
        assertTrue("Wrong second import", ie.extract(javaFile).contains("java.lang.Object"));
    }

    @Test
    public void testExtractStaticImports() {
        String javaFile = "import static org.junit.Assert.assertTrue;";

        assertTrue("Static import parsing failure", ie.extract(javaFile).contains("org.junit.Assert"));
    }

    @Test
    public void shouldTruncateWhitespacesInTheEnd() {
        String javaFile = "import   static    org.junit.Assert.assertTrue   ;  \n" +
                "import    java.lang.Object  ;  ";

        assertTrue("Wrong parsing with whitespaces", ie.extract(javaFile).contains("org.junit.Assert") &&
                ie.extract(javaFile).contains("java.lang.Object"));
    }

    @Test
    public void shouldTruncateSemicolons() {
        String javaFile = "import org.junit.Assert;  ; ;;";

        assertTrue("Wrong parsing with multiple semicolons", ie.extract(javaFile).contains("org.junit.Assert"));
    }

}