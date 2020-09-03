package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.basePage.PageResult;
import com.zxjx.entity.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:35
 */
public interface ITeacherService extends IBaseService<Teacher> {
    /**
     * <p>
     * 审核老师通过
     * </p>
     *
     * @author liuzhixiang 2020/04/11 16:41
     */
    String auditPass(HttpServletRequest request,Long teacherId);
    
    /**
     * <p>
     * 发工资
     * </p>
     *
     * @param request
     * @param salary 发工资
     * @param teacherId
     * @return 发送消息
     * @author liuzhixiang 2020/04/14 13:17
     */
    String sentSalary(HttpServletRequest request, Long teacherId, BigDecimal salary);

    /**
     * <p>
     *分页查询老师教授的班级
     * </p>
     *
     * @param request
     * @param pageRequest
     * @param teacherId 老师id
     * @author liuzhixiang 2020/04/19 20:58
     */
    PageResult queryPagesTeacherTeachingClasses(HttpServletRequest request, PageRequest pageRequest,Long teacherId);
}
