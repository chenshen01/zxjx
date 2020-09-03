package com.zxjx.base;

import com.zxjx.utils.ObjectUtil;
import com.zxjx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:11
 */
@Slf4j
public class BaseUpdateProvider {

    public String updateByPrimaryKey(Map<String,Object> params){
        Object object = params.get("params");
        String tableName = SqlHelper.getTableNameByEntity(object);
        String primaryKey = SqlHelper.getTablePrimaryKey(object);
        Map<String, Object> map = ObjectUtil.convertBeanToMap(object,true);
        if (map.size() == 0) {
            log.debug("对象为属性都为空");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("update " + tableName + "  t");
        sql.append(" set ");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        StringBuffer setSql = new StringBuffer();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            if (!String.valueOf(next.getKey()).equals(primaryKey)) {
                setSql.append("t." + StringUtils.convertCamelCaseToUnderline(next.getKey()) + " = ");
                setSql.append(SqlHelper.getSqlValue(next.getValue()) + ",");
            }
        }
        if (setSql.toString().endsWith(",")) {
            sql.append(setSql.substring(0,setSql.length() - 1));
        }
        sql.append(" where t." + StringUtils.convertCamelCaseToUnderline(primaryKey) + " = " + SqlHelper.getSqlValue(map.get(primaryKey)));
        String result = sql.toString();
        log.debug("com.zxjx.base.BaseUpdateProvider.updateByPrimaryKey:" + result);
        return result;
    }
}
