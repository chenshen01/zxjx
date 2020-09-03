package com.zxjx.base;

import com.zxjx.utils.ObjectUtil;
import com.zxjx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:11
 */
@Slf4j
public class BaseSelectProvider {

    private static final String AND = "and";

    public String selectById(Map<String,Object> params){
        Object object = params.get("params");
        // 1.获得实体类对应的表名
        String tableName = SqlHelper.getTableNameByEntity(object);
        // 2.获取实体类对应表名的主键
        String primaryKey = SqlHelper.getTablePrimaryKey(object);
        // 3.获得要查询时拼接的条件
        Map selectFields= ObjectUtil.convertBeanToMap(object,false);
        if (selectFields.size() == 0) {
            throw new RuntimeException("传入的对象属性都为空");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select * from  " + tableName + " t");
        sql.append(" where " + " t." + StringUtils.convertCamelCaseToUnderline(primaryKey) + " = " + SqlHelper.getSqlValue(selectFields.get(primaryKey)));
        String result = sql.toString();
        log.debug("com.zxjx.base.BaseSelectProvider.selectById:" + result);
        return result;
    }

    public String selectCount(Map<String,Object> params){
        Object object = params.get("params");
        // 1.获得实体类对应的表名
        String tableName = SqlHelper.getTableNameByEntity(object);
        // 2.创建sql
        StringBuffer sql = new StringBuffer();
        sql.append("select count(*)  from  " + tableName);
        String result = sql.toString();
        log.debug("com.zxjx.base.BaseSelectProvider.selectCount:" + result);
        return result;
    }

    public String selectAll(Map<String,Object> params){
        Object object = params.get("params");
        // 1.获得实体类对应的表名
        String tableName = SqlHelper.getTableNameByEntity(object);
        // 2.创建sql
        StringBuffer sql = new StringBuffer();
        sql.append("select * from  " + tableName);
        String result = sql.toString();
        log.debug("com.zxjx.base.BaseSelectProvider.selectAll:" + result);
        return result;
    }

    public String selectByCondition(Map<String,Object> params){
        Object object = params.get("params");
        String tableName = SqlHelper.getTableNameByEntity(object);
        List<String> allColumns = SqlHelper.getAllColumns(object);
        Map<String, Object> selectFields = ObjectUtil.convertBeanToMap(object, false);
        StringBuffer selectColumns = new StringBuffer();
        for (int i = 0;i < allColumns.size();i++) {
            if (i == (allColumns.size() - 1)) {
                selectColumns.append(" t." + allColumns.get(i) + " as " +  StringUtils.convertUnderlineToCamelCase(allColumns.get(i)) + " ");
            } else {
                selectColumns.append(" t." + allColumns.get(i) + " as " + StringUtils.convertUnderlineToCamelCase(allColumns.get(i))+ ",");
            }
        }
        StringBuffer sql = new StringBuffer();
        String result = "";
        sql.append("select ");
        sql.append(selectColumns);
        sql.append("from  " + tableName +" t");
        if (selectFields.size() > 0) {
            sql.append(" where ");
            Iterator<Map.Entry<String, Object>> iterator = selectFields.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                sql.append("t."+StringUtils.convertCamelCaseToUnderline(next.getKey()));
                sql.append(" = ");
                sql.append(SqlHelper.getSqlValue(next.getValue()));
                sql.append(" and ");
            }
            result = sql.toString();
            if (result.trim().endsWith(AND)) {
                result =  result.substring(0,sql.length() - 4);
            }
        } else {
            result = sql.toString();
        }
        log.debug("com.zxjx.base.BaseSelectProvider.selectByCondition:" + result);
        return result;
    }
}
