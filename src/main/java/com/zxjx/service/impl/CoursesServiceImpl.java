package com.zxjx.service.impl;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Courses;
import com.zxjx.entity.Teacher;
import com.zxjx.service.ICoursesService;
import com.zxjx.service.ITeacherService;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CoursesServiceImpl extends BaseServiceImpl<Courses> implements ICoursesService {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ITeacherService teacherService;

    /**
     * <p>
     *审批课程
     * </p>
     *
     * @param request
     * @param courseId
     * @return 审批状态
     * @author liuzhixiang 2020/04/17 17:20
     */
    @Override
    public String auditCourse(HttpServletRequest request, Long courseId){
        Courses course = this.selectById(Courses.builder().id(courseId).build());
        if (Courses.COURSE_STATUS_AUDITED.equals(course.getStatus())) {
            return BaseMassage.AUDIT_REPEAT;
        } else if (Courses.COURSE_STATUS_REJECTED.equals(course.getStatus())) {
            return BaseMassage.AUDIT_REJECT;
        } else {
            course.setStatus(Courses.COURSE_STATUS_AUDITED);
            this.updateByPrimaryKey(course);
            String email = teacherService.selectById(Teacher.builder().id(course.getTeachedBy()).build()).getEmail();
            mailUtil.sendMail(email,BaseMassage.SUBJECT,"您开设的" + course.getCourseName() + "在" +
                    DateUtil.getCurrentDateTamp() + "审批通过！");
            return BaseMassage.AUDIT_SUCCESS;
        }
    }

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
    @Override
     public String rejectCourse(HttpServletRequest request,Long courseId){
        Courses course = this.selectById(Courses.builder().id(courseId).build());
        if (Courses.COURSE_STATUS_REJECTED.equals(course.getStatus())) {
            return BaseMassage.COURSE_REJECTED_DUP;
        } else if (Courses.COURSE_STATUS_AUDITED.equals(course.getStatus())) {
            return BaseMassage.COURSE_AUDITED;
        } else {
            course.setStatus(Courses.COURSE_STATUS_REJECTED);
            this.updateByPrimaryKey(course);
            String email = teacherService.selectById(Teacher.builder().id(course.getTeachedBy()).build()).getEmail();
            mailUtil.sendMail(email,BaseMassage.SUBJECT,"您开设的" + course.getCourseName() + "在" +
                    DateUtil.getCurrentDateTamp() + "被拒绝！");
            return BaseMassage.COURSE_REJECTED;
        }
     }
}
