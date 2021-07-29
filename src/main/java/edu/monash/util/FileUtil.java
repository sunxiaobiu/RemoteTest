package edu.monash.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static void copyFile(String originFilePath, String toFilePath, String fileName) throws IOException {
        File originFile = new File(originFilePath+fileName);

        File desFile =new File(toFilePath+fileName);

        FileUtils.copyFile(originFile, desFile);

    }

    public static void getFileNameList(String outputFilePath, List<String> txtFileNames) throws IOException {
        Stream<Path> paths = Files.walk(Paths.get(outputFilePath));

        List<String> result = paths.filter(Files::isRegularFile)
                .map(x -> x.getFileName().toString()).collect(Collectors.toList());

        result.stream().forEach(filename -> {
            txtFileNames.add(filename);
        });
    }

    public static void getTestFileList(String outputFilePath, List<String> txtFileNames) throws IOException {
        Stream<Path> paths = Files.walk(Paths.get(outputFilePath));

        List<String> result = paths.filter(Files::isRegularFile)
                .map(x -> x.toAbsolutePath().toString()).collect(Collectors.toList());

        result.stream().forEach(filename -> {
            if (filename.endsWith("Test.java") || filename.endsWith("Tests.java")) {
                txtFileNames.add(filename);
            }
        });
    }
}
