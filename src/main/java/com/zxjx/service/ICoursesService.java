package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.Courses;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:20
 */
public interface ICoursesService extends IBaseService<Courses> {
    /**
     * <p>
     * 审批课程通过
     * </p>
     *
     * @param request
     * @param courseId
     * @return 审批状态
     * @author liuzhixiang 2020/04/17 17:20
     */
    String auditCourse(HttpServletRequest request,Long courseId);

    /**
     * <p>
     * 拒绝课程通过
     * </p>
     *
     * @param request
     * @param courseId
     * @return 审批状态
     * @author liuzhixiang 2020/04/17 17:20
     */
    String rejectCourse(HttpServletRequest request,Long courseId);
}
