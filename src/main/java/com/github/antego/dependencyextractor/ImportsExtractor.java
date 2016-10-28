package com.github.antego.dependencyextractor;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ImportsExtractor {

    public Set<String> extract(String javaFile) {
        Scanner scanner = new Scanner(javaFile);
        Set<String> imports = new HashSet<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("import ")) {
                line = line.replace("import ", "");
                line = line.trim();
                if (line.startsWith("static ")) {
                    line = line.replace("static ", "");
                    line = line.trim();
                    int lastDotIndex = line.lastIndexOf(".");
                    line = line.substring(0, lastDotIndex);
                } else {
                    int indexOfFirstSemicolon = line.indexOf(";");
                    line = line.substring(0, indexOfFirstSemicolon);
                    // remove whitespaces after class
                    line = line.trim();
                }
                imports.add(line);
            }
        }
        return imports;
    }
}
