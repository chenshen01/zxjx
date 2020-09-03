package com.zxjx.utils;

import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.DateUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/03/18 9:28
 */
public class ObjectUtil {


    /**
     * <p>
     *将bean转成map
     * </p>
     *
     * @param containsNull 是否将值为null的属性也加入到Map中
     * @author liuzhixiang 2020/03/18 10:02
     */
    public static Map<String,Object> convertBeanToMap(Object object,boolean containsNull){
        Map<String,Object> map = new HashMap();
        if (object == null) {
            return null;
        }
        Class<?> objectClass = object.getClass();
        Field[] declaredFields = objectClass.getDeclaredFields();
        for (Field field : declaredFields) {
            // 过滤掉静态属性
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            String name = field.getName();
            try {
                Object value = field.get(object);
                if (containsNull) {
                    map.put(name,value);
                } else {
                    if (value != null) {
                        map.put(name,value);
                    }
                }
            } catch (IllegalAccessException  e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static Object convertMapToBean(Class<?> clazz,Map map){
        try {
            //获得class类型
            Object obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                //获得私有变量
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
            return obj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object convert2Bean(Class type, Map map) {
        Object obj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : propertyDescriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    value = convertChangeType(descriptor, value);
                    descriptor.getWriteMethod().invoke(obj, value);
                }
            }
        } catch (IntrospectionException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
             e.printStackTrace();
        }
        return obj;
    }
    private static Object convertChangeType(PropertyDescriptor descriptor, Object value) {
        Object obj = null;
        if (descriptor.getPropertyType().equals(Byte.class) && !ObjectUtils.isEmpty(value)) {
            //Byte类型
            obj = Byte.parseByte(value.toString());
        } else if (descriptor.getPropertyType().equals(Short.class) && !ObjectUtils.isEmpty(value)) {
            //Short类型
            obj = Short.parseShort(value.toString());
        } else if (descriptor.getPropertyType().equals(Integer.class) && !ObjectUtils.isEmpty(value)) {
            //Integer类型
            obj = Integer.parseInt(value.toString());
        } else if (descriptor.getPropertyType().equals(Long.class) && !ObjectUtils.isEmpty(value)) {
            //Long类型
            obj = Long.parseLong(value.toString());
        } else if (descriptor.getPropertyType().equals(Float.class) && !ObjectUtils.isEmpty(value)) {
            //Float类型
            obj = Float.parseFloat(value.toString());
        } else if (descriptor.getPropertyType().equals(Double.class) && !ObjectUtils.isEmpty(value)) {
            //Double类型
            obj = Double.parseDouble(value.toString());
        } else if (descriptor.getPropertyType().equals(String.class) && !ObjectUtils.isEmpty(value)) {
            //String类型
            obj = value.toString();
        } else if (descriptor.getPropertyType().equals(Boolean.class) && !ObjectUtils.isEmpty(value)) {
            //Boolean类型
            obj = Boolean.parseBoolean(value.toString());
        } else if (descriptor.getPropertyType().equals(BigDecimal.class) && !ObjectUtils.isEmpty(value)) {
            //BigDecimal类型
            obj = new BigDecimal(value.toString());
        } else if (descriptor.getPropertyType().equals(Date.class) && !ObjectUtils.isEmpty(value)) {
            //java.util.Date类型
            obj = DateUtil.getSimpleDate(value.toString());
        } else if (descriptor.getPropertyType().equals(java.sql.Date.class) && !ObjectUtils.isEmpty(value)) {
            //java.sql.Date类型
            obj = java.sql.Date.valueOf(value.toString());
        }
        return obj;
    }
}
