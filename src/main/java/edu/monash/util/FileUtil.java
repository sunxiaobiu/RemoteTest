package edu.monash.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static void copyFile(String originFilePath, String toFilePath, String fileName) throws IOException {
        File originFile = new File(originFilePath+fileName);

        File desFile =new File(toFilePath+fileName);

        FileUtils.copyFile(originFile, desFile);

    }
}
