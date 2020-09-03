package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.CourseClass;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:37
 */
public interface ICourseClassService extends IBaseService<CourseClass> {

    /**
     * <p>
     *查询所有的CourseClass
     * </p>
     *
     * @return
     * @author liuzhixiang 2020/08/20 17:04
     */
    List<CourseClass>  queryAllCourseClass();
}
