package edu.monash.util;

import java.io.*;
import java.net.Socket;

public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public static void closeQuietly(Socket c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    public static String getString(InputStream inputStream){
        BufferedReader bufferedReader;
        StringBuffer strBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
                strBuffer.append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuffer.toString();
    }

    public static String fileRead(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}
