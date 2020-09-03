package com.zxjx.utils;

/**
 * description
 *
 * @author liuzhixiang 2020/04/11 22:02
 */
public class FileUtil {

    public static final  String FILE_ABSOLUTE_LOCATION = "F:/workspace-graduate/src/main/resources/static";

    public static String getRelativeLocation(String location){
        String result = "";
        if (location.indexOf(FILE_ABSOLUTE_LOCATION) > -1) {
            result = location.replaceAll(FILE_ABSOLUTE_LOCATION,"").trim();
        } else {
            return location;
        }
        return result;
    }
}
