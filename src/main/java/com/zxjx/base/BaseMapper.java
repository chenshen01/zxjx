package com.zxjx.base;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description 所有的mapper继承该类即有了单表所需要的增删查改等功能
 *
 * @author liuzhixiang 2020/04/02 16:05
 */
@Mapper
public interface BaseMapper<T> {

    /**
     * <p>
     *查询单条记录
     * </p>
     *
     * @param t
     * @return T
     * @author liuzhixiang 2020/04/02 16:21
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectById")
    T selectById(@Param("params") T t);

    /**
     * <p>
     *获取一张表的总记录数
     * </p>
     *
     * @param t
     * @return
     * @author liuzhixiang 2020/04/02 17:58
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectCount")
    int selectCount(@Param("params")T t);

    /**
     * <p>
     *查询所有记录
     * </p>
     *
     * @author liuzhixiang 2020/04/02 18:12
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectAll")
    List<T>  selectAll(@Param("params")T t);

    /**
     * <p>
     *按照条件进行查询
     * </p>
     *
     * @author liuzhixiang 2020/04/05 13:07
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectByCondition")
    List<T>  selectByCondition(@Param("params")T t);

    /**
     * <p>
     *插入对象
     * </p>
     *
     * @author liuzhixiang 2020/04/03 11:02
     */
    @InsertProvider(type = BaseInsertProvider.class,method = "insert")
    int insert(@Param("params")T t);

    /**
     * <p>
     *更新所有的字段
     * </p>
     *
     * @author liuzhixiang 2020/04/03 11:04
     */
    @UpdateProvider(type = BaseUpdateProvider.class,method = "updateByPrimaryKey")
    int updateByPrimaryKey(@Param("params")T t);

    /**
     * <p>
     *更新不为空的字段
     * </p>
     *
     * @author liuzhixiang 2020/04/03 11:04
     */
    int updateByPrimaryKeySelective(@Param("params") T t);

     /**
      * <p>
      *按id删除对象
      * </p>
      *
      * @author liuzhixiang 2020/04/03 10:59
      */
    @DeleteProvider(type = BaseDeleteProvider.class,method = "deleteByPrimaryKey")
    int deleteByPrimaryKey(@Param("params")T t);
}
