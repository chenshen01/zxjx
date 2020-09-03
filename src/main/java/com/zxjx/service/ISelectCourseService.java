package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.SelectCourse;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:09
 */
public interface ISelectCourseService extends IBaseService<SelectCourse> {

    /**
     * <p>
     * 选课
     * </p>
     *
     * @param courseId
     * @param classId
     * @param studentId
     * @return message
     * @author liuzhixiang 2020/05/11 10:50
     */
    String selectClass(Long studentId,Long courseId,Long classId);
}
