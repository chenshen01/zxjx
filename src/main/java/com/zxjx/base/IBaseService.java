package com.zxjx.base;

import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.basePage.PageResult;
import com.zxjx.entity.Student;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 10:44
 */
public interface IBaseService<T> {
    /**
     * <p>
     *查询单条记录
     * </p>
     *
     * @param t
     * @return T
     * @author liuzhixiang 2020/04/02 16:21
     */
    T selectById(T t);

    /**
     * <p>
     *查询所有记录
     * </p>
     *
     * @author liuzhixiang 2020/04/02 18:12
     */
    List<T> selectAll(T t);

    /**
     * <p>
     *插入对象
     * </p>
     *
     * @author liuzhixiang 2020/04/03 11:02
     */
    int insert(T t);

    /**
     * <p>
     *更新所有的字段
     * </p>
     *
     * @author liuzhixiang 2020/04/03 11:04
     */
    int updateByPrimaryKey(T t);

    /**
     * <p>
     *按id删除对象
     * </p>
     *
     * @author liuzhixiang 2020/04/03 10:59
     */
    int deleteByPrimaryKey(T t);

    /**
     * <p>
     *按照条件进行查询
     * </p>
     *
     * @author liuzhixiang 2020/04/05 13:08
     */
    List<T>  selectByCondition(T t);

    /**
     * <p>
     * 根据传入的条件进行分页查询
     * </p>
     *
     * @author liuzhixiang 2020/04/10 22:12
     */
    PageResult selectPagesByCondition(PageRequest pageRequest, T t);

}
