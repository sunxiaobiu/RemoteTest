package edu.monash.util;

import com.mysql.cj.util.StringUtils;

import java.util.Date;

public class ExceptionUtil {

    public static void runtimeExpWithNullCheck(int input, String message){
        if(input == 0){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExpWithNullCheck(String input, String message){
        if(StringUtils.isNullOrEmpty(input)){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExp(String message){
        throw new RuntimeException(message);
    }

}
