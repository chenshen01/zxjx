package com.zxjx.service.impl;

import com.zxjx.base.BaseConstant;
import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Student;
import com.zxjx.entity.Teacher;
import com.zxjx.entity.VacationRequsition;
import com.zxjx.service.IStudentService;
import com.zxjx.service.ITeacherService;
import com.zxjx.service.IVacationRequsitionService;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VacationRequsitionServiceImpl extends BaseServiceImpl<VacationRequsition> implements IVacationRequsitionService {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private MailUtil mailUtil;
    /**
     * <p>
     *审批请假申请
     * </p>
     *
     * @param auditBy
     * @param vacationRequsitionId
     * @return message
     * @author liuzhixiang 2020/05/05 21:12
     */
    @Override
    public String auditVacationRequsition(Long auditBy,Long vacationRequsitionId){
        VacationRequsition vacationRequsition = this.selectById(VacationRequsition.builder().id(vacationRequsitionId)
            .build());
        if (VacationRequsition.VACATION_REQUSITION_PASS.equals(vacationRequsition.getStatus())) {
            return BaseMassage.AUDIT_ADJUSTCLASSREQUSITION_DUP;
        }
        vacationRequsition.setAuditBy(auditBy);
        vacationRequsition.setVacationTimeFrom(DateUtil.toSimpleDate(vacationRequsition.getVacationTimeFrom()));
        vacationRequsition.setVacationTimeTo(DateUtil.toSimpleDate(vacationRequsition.getVacationTimeTo()));
        vacationRequsition.setAuditTime(DateUtil.getCurrentDate());
        vacationRequsition.setStatus(VacationRequsition.VACATION_REQUSITION_PASS);
        this.updateByPrimaryKey(vacationRequsition);
        String role = vacationRequsition.getRole();
        String mailTo = "";
        if (BaseConstant.STUDENT.equals(role)) {
            Student student = studentService.selectById(Student.builder().id(vacationRequsition.getRequisitionBy())
                .build());
            mailTo = student.getEmail();
        } else {
            Teacher teacher = teacherService.selectById(Teacher.builder().id(vacationRequsition.getRequisitionBy())
                .build());
            mailTo = teacher.getEmail();
        }
        mailUtil.sendMail(mailTo,BaseMassage.SUBJECT,"您的请假申请已被审批");
        return BaseMassage.AUDIT_SUCCESS;
    }

    /**
     * <p>
     * 撤销请假申请
     * </p>
     *
     * @param vacationRequsitionId
     * @return message
     * @author liuzhixiang 2020/05/11 22:18
     */
    @Override
    public String cancelVacationRequsition(Long vacationRequsitionId){
        VacationRequsition vacationRequsition = this.selectById(VacationRequsition.builder().id(vacationRequsitionId)
            .build());
        String status = vacationRequsition.getStatus();
        if (VacationRequsition.VACATION_REQUSITION_WAIT.equals(status)) {
            this.deleteByPrimaryKey(vacationRequsition);
            return BaseMassage.BACK_SUCCESS;
        } else {
            return BaseMassage.BACK_FAIL;
        }
    }
}
