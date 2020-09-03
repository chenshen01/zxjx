package com.zxjx.base;

import com.zxjx.utils.ObjectUtil;
import com.zxjx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:12
 */
@Slf4j
public class BaseInsertProvider {
    public String insert(Map<String,Object> params){
        Object object = params.get("params");
        Map<String, Object> map = ObjectUtil.convertBeanToMap(object, true);
        List<String> fields = SqlHelper.getAllFieldWithoutIdField(object);
        StringBuffer sql = new StringBuffer();
        sql.append("insert  into ");
        sql.append(SqlHelper.getTableNameByEntity(object));
        sql.append("(");
        for (int i = 0; i < fields.size();i++) {
            if (i == (fields.size() - 1)) {
                sql.append(StringUtils.convertCamelCaseToUnderline(fields.get(i)));
            } else {
                sql.append(StringUtils.convertCamelCaseToUnderline(fields.get(i)) + ",");
            }
        }
        sql.append(")");
        sql.append(" values");
        sql.append("(");
        for (int i = 0; i < fields.size();i++) {
            if (i == (fields.size() - 1)) {
                sql.append(SqlHelper.getSqlValue(map.get(fields.get(i))));
            } else {
                sql.append(SqlHelper.getSqlValue(map.get(fields.get(i))) + ",");
            }
        }
        sql.append(")");
        String result = sql.toString();
        log.debug("BaseInsertProvider.insert:" + result);
        return result;
    }
}
