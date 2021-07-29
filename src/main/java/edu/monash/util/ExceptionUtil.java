package edu.monash.util;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.Date;

public class ExceptionUtil {

    public static void runtimeExpWithNullCheck(int input, String message){
        if(input == 0){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExpWithNullCheck(Collection input, String message){
        if(CollectionUtils.isEmpty(input)){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExpWithNullCheck(Boolean input, String message){
        if(input == null){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExpWithNonNullCheck(String input, String message){
        if(input != null){
            throw new RuntimeException(message);
        }
    }

    public static void runtimeExpWithNonNullCheck(Date date, String message) {
        if (date != null) {
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
