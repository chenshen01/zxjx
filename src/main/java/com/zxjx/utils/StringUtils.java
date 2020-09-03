package com.zxjx.utils;

/**
 * description
 *
 * @author liuzhixiang 2020/03/07 11:31
 */
public class StringUtils {

    private final static char UNDERLINE = '_';

    private StringUtils(){

    }
    /**
     * <p>
     *驼峰命名转化成下划线格式
     * </p>
     *
     * @param string 需要转化的字符串
     * @author liuzhixiang 2020/03/07 11:33
     */
    public static String convertCamelCaseToUnderline(String string){
        if (isEmpity(string)) {
            return null;
        }
        if (string.equals(string.toLowerCase())) {
            return string;
        }
        char [] chars = string.toCharArray();
        int resultLength = 0;
        for (int i = 0;i<chars.length;i++){
           if (chars[i] >= 'A' && chars[i] <= 'Z') {
               resultLength++;
           }
        }
        char [] resultChars = new char[chars.length + resultLength];
        int ascii;
        int resultIndex = 0;
        for (int i = 0;i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                ascii = (int)chars[i] + 32;
                if (resultIndex == 0) {
                    resultChars[resultIndex] = (char) ascii;
                } else {
                    resultChars[resultIndex] = UNDERLINE;
                    resultChars[resultIndex + 1] = (char) ascii;
                    resultIndex = resultIndex + 2;
                }
            } else {
                resultChars[resultIndex] = chars[i];
                resultIndex++;
            }
        }
        return new String(resultChars);
    }

    public static boolean isEmpity(String string){
        if (string == null) {
            return true;
        }
        if ("".equals(string.trim())) {
            return true;
        }
        return false;
    }

    public static String convertUnderlineToCamelCase(String string){
        if (isEmpity(string)) {
            return null;
        }
        char[] chars = string.toCharArray();
        int resultLength = 0;
        for (int i = 0;i < chars.length;i++){
            if (chars[i] == UNDERLINE) {
                resultLength++;
            }
        }
        if (resultLength == 0) {
            return string;
        }
        char[] resultChars = new char[chars.length - resultLength];
        int resultIndex = 0;
        for (int i = 0;i < chars.length;i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                resultChars[resultIndex] = chars[i];
                resultIndex++;
            } else if (chars[i] == UNDERLINE) {
                resultChars[resultIndex] = (char)((int)chars[i + 1] - 32);
                i = i + 1;
                resultIndex++;
            }
        }
        return new String(resultChars);
    }

    public static String clear(String string,String regex){
        if (string.contains(regex)) {
            return string.replace(regex,"");
        }
        return string;
    }
}
