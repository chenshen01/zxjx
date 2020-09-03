package com.zxjx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.basePage.PageResult;
import com.zxjx.base.basePage.PageUtil;
import com.zxjx.entity.Classes;
import com.zxjx.entity.PlatForm;
import com.zxjx.entity.Teacher;
import com.zxjx.exception.serviceexception.PlatFormException;
import com.zxjx.mapper.ClassesMapper;
import com.zxjx.mapper.PlatFormMapper;
import com.zxjx.mapper.TeacherMapper;
import com.zxjx.service.ITeacherService;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:36
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements ITeacherService {

    @Autowired
    private TeacherMapper mapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private PlatFormMapper platFormMapper;

    @Autowired
    private ClassesMapper classesMapper;
    /**
     * <p>
     * 审核老师通过
     * </p>
     *
     * @author liuzhixiang 2020/04/11 16:41
     */
    @Override
    public String auditPass(HttpServletRequest request, Long teacherId){
        if (teacherId == null) {
            throw new RuntimeException("请确定要通过的老师");
        }
        Teacher teacher = mapper.selectById(Teacher.builder().id(teacherId).build());
        if (Teacher.AUDITED.equals(teacher.getStatus())) {
            return BaseMassage.AUDIT_REPEAT;
        }
        teacher.setStatus(Teacher.AUDITED);
        int result = mapper.updateByPrimaryKey(teacher);
        if (result > 0) {
            mailUtil.sendMail(teacher.getEmail(),BaseMassage.SUBJECT,"您的申请已经审核通过，骏波在线教育欢迎您的加入!您的教工号为"
                    + teacher.getTeacherCode());
            return BaseMassage.AUDIT_SUCCESS;
        } else {
            return BaseMassage.AUDIT_FAIL;
        }
    }

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
    @Override
    public String sentSalary(HttpServletRequest request, Long teacherId, BigDecimal salary){
        Teacher teacher = mapper.selectById(Teacher.builder().id(teacherId).build());
        if (teacher.getSalary() == null) {
            teacher.setSalary(BigDecimal.ZERO);
        }
        List<PlatForm> platForms = platFormMapper.selectAll(PlatForm.builder().build());
        PlatForm platForm = platForms.get(0);
        // 如果工资大于平台账户余额则报错
        if (platForm.getAccount().compareTo(salary) == -1){
            throw new PlatFormException(BaseMassage.PLATFORM_ACCOUNT_IS_NOT_ENOUGH);
        } else {
            // 更新
            platForm.setAccount(platForm.getAccount().subtract(salary));
            platFormMapper.updateByPrimaryKey(platForm);
            teacher.setSalary(teacher.getSalary().add(salary));
            mapper.updateByPrimaryKey(teacher);
            // 给老师发送邮件
            mailUtil.sendMail(teacher.getEmail(),BaseMassage.SUBJECT,"您的工资已在" + DateUtil.getCurrentDateTamp() + "发放，具体金额为"
                   + salary + "请尽快登陆系统确认！");
            return BaseMassage.AUDIT_SUCCESS;
        }
    }

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
    @Override
    public PageResult queryPagesTeacherTeachingClasses(HttpServletRequest request, PageRequest pageRequest,
                                                       Long teacherId){
        return PageUtil.getPageResult(pageRequest,getPageInfo(pageRequest,teacherId));
    }
    private PageInfo<Classes> getPageInfo(PageRequest pageRequest, Long teacherId){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Classes> classes = classesMapper.queryTeacherTeachingClasses(teacherId);
        return new PageInfo<Classes>(classes);
    }
}
