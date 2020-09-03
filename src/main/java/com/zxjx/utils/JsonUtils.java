package com.zxjx.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description  解析Json串
 *
 * @author liuzhixiang 2020/08/23 21:07
 */
public class JsonUtils {

    private final static String RIGHT = "[";

    private JsonUtils(){

    }

    public static Object parseJsonString(String values,Class<?> clazz){
        if (StringUtils.isEmpity(values)) {
            throw new RuntimeException("请传入有效的json串");
        }
        String substring = "";
        if (values.contains(RIGHT)) {
            substring = values.substring(2, values.length() - 2);
        } else {
            substring = values.substring(1,values.length() - 1);
        }
        List<Map> mapList = new ArrayList<>();
        Map<String,Object> entryMap = new HashMap<>();
        // 获得所有的对象JSON
        String [] strings = substring.split("},");
        int index = 0;
        for (String entry : strings) {
            String keyAndValueUnclear = "";
            // 去除大括号
            if (index < strings.length - 1) {
                keyAndValueUnclear = entry.substring(1, entry.length());
            } else {
                if (values.contains("[")) {
                    keyAndValueUnclear = entry.substring(1, entry.length() - 2);
                } else {
                    keyAndValueUnclear = entry.substring(1, entry.length() - 1);
                }
            }
            index++;
            // 获取所有属性以及属性值
            String[] splits = keyAndValueUnclear.split(",");
            // 将值和属性值设置进map
            for (String string : splits) {
                String[] keyAndValueClear = string.split(":");
                String field = StringUtils.clear(keyAndValueClear[0],"\\\"");
                String fieldValue = StringUtils.clear(keyAndValueClear[1],"\\\"");
                entryMap.put(field,fieldValue);
            }
            mapList.add(entryMap);
            entryMap = new HashMap<>();
        }
        List<Object> results = new ArrayList<>();
        if (!mapList.isEmpty()) {
            mapList.forEach( map -> {
                Object result = ObjectUtil.convert2Bean(clazz, map);
                results.add(result);
            });
        }
        return results;
    }
}
