package com.zxjx.service.impl;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:25
 */

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.AdjustClassRequsition;
import com.zxjx.entity.Teacher;
import com.zxjx.service.IAdjustClassRequsitionService;
import com.zxjx.service.ITeacherService;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdjustClassRequsitionServiceImpl extends BaseServiceImpl<AdjustClassRequsition> implements IAdjustClassRequsitionService {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private MailUtil mailUtil;
    /**
     * <p>
     *审批调课申请
     * </p>
     *
     * @param adjustClassRequsitionId
     * @param auditBy
     * @return message
     * @author liuzhixiang 2020/05/05 21:01
     */
    @Override
    public String auditAdjustClassRequsition(Long adjustClassRequsitionId,Long auditBy){
        // 1、审批
        AdjustClassRequsition adjustClassRequsition = this.selectById(AdjustClassRequsition.builder().id(adjustClassRequsitionId)
            .build());
        // 2、调课申请不能重复审批
        if (AdjustClassRequsition.PASS.equals(adjustClassRequsition.getStatus())) {
            return BaseMassage.AUDIT_ADJUSTCLASSREQUSITION_DUP;
        }
        adjustClassRequsition.setAuditBy(auditBy);
        adjustClassRequsition.setAuditTime(DateUtil.getCurrentDate());
        adjustClassRequsition.setStatus(AdjustClassRequsition.PASS);
        this.updateByPrimaryKey(adjustClassRequsition);
        // 3、给老师发邮件
        Teacher teacher = teacherService.selectById(Teacher.builder().id(adjustClassRequsition.getRequisitionBy()).build());
        mailUtil.sendMail(teacher.getEmail(), BaseMassage.SUBJECT,"你的调课申请已审批通过");
        return BaseMassage.AUDIT_SUCCESS;
    }

    /**
     * <p>
     *撤回调课申请
     * </p>
     *
     * @author liuzhixiang 2020/05/12 10:59
     */
    @Override
    public String cancelAdjustClassRequsition(Long adjustClassRequsitionId){
        AdjustClassRequsition adjustClassRequsition = this.selectById(AdjustClassRequsition.builder()
            .id(adjustClassRequsitionId).build());
        // 1、校验是否被审批
        String status = adjustClassRequsition.getStatus();
        if (AdjustClassRequsition.PASS.equals(status)) {
            return BaseMassage.BACK_FAIL;
        }
        // 2、撤回调申请
        this.deleteByPrimaryKey(adjustClassRequsition);
        return BaseMassage.BACK_SUCCESS;
    }
}
