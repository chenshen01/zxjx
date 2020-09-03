package com.zxjx.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zxjx.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description
 *
 * @author liuzhixiang 2020/03/08 11:40
 */
public class SqlHelper {
    private static Logger logger = LoggerFactory.getLogger(SqlHelper.class);
    /**
     * <p>
     * 通过实体类获取表名
     * </p>
     *
     * @author liuzhixiang 2020/03/08 11:40
     */
    public static String getTableNameByEntity(Object object){
        logger.debug("正在获取:" + object.getClass().getName() + "实体对应的表名");
        if (object == null) {
            throw new NullPointerException();
        }
        Class clazz = object.getClass();
        Table table = (Table) clazz.getAnnotation(Table.class);
        if (table != null) {
            String tableName = table.name();
            return tableName;
        } else {
            throw new RuntimeException("该实体类上没有@Table注解!");
        }
    }

    /**
     * <p>
     *获取表的主键
     * </p>
     *
     * @author liuzhixiang 2020/03/16 19:34
     */
    public static String getTablePrimaryKey(Object object){
        logger.debug("正在获取:" + object.getClass().getName() + "实体对应的表的主键");
        if (object == null) {
            throw new NullPointerException();
        }
        Class clazz = object.getClass();
        Field [] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getName();
            }
        }
        return null;
    }

    /**
     * <p>
     *将java类型的值转化成mysql对应的值
     * </p>
     *
     * @author liuzhixiang 2020/03/16 19:35
     */
    public static Object getSqlValue(Object value){
        if (value instanceof Long) {
            return value;
        } else if (value instanceof String) {
            return "\'" + value + "\'";
        } else if (value instanceof Integer) {
            return value;
        } else if (value instanceof Date) {
            return "\'" + value + "\'";
        }
        return value;
    }

    public static List<String> getAllColumnsWithoutPrimaryKey(Object object){
        List<String> columns = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
              continue;
            }
            if (field.isAnnotationPresent(Column.class)) {
                columns.add(StringUtils.convertCamelCaseToUnderline(field.getName()));
            }
        }
        return columns;
    }

    public static List<String> getAllColumns(Object object){
        List<String> columns = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                //columns.add(StringUtils.convertCamelCaseToUnderline(field.getName()));
                Column column = field.getAnnotation(Column.class);
                columns.add(column.name());
            }
        }
        return columns;
    }

    public static List<String> getAllFieldWithoutIdField(Object object){
        List<String> columns = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }
            if (field.isAnnotationPresent(Column.class)) {
                columns.add(field.getName());
            }
        }
        return columns;
    }
}
