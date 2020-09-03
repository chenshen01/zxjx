package com.zxjx.base;

import com.zxjx.utils.ObjectUtil;
import com.zxjx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:12
 */
@Slf4j
public class BaseDeleteProvider {

    public String deleteByPrimaryKey(Map<String,Object> params){
        Object object = params.get("params");
        String tableName = SqlHelper.getTableNameByEntity(object);
        String primaryKey = SqlHelper.getTablePrimaryKey(object);
        Map<String, Object> values = ObjectUtil.convertBeanToMap(object, false);
        if (values.get(primaryKey) == null) {
            throw new RuntimeException("id为空!");
        }
        StringBuffer sql = new StringBuffer();
        sql.append("delete  from ");
        sql.append(StringUtils.convertCamelCaseToUnderline(tableName));
        sql.append("  where " + StringUtils.convertCamelCaseToUnderline(primaryKey) + " = ");
        sql.append(SqlHelper.getSqlValue(values.get(primaryKey)));
        String result = sql.toString();
        log.debug("com.zxjx.base.BaseDeleteProvider.deleteByPrimaryKey:" + result);
        return result;
    }
}
