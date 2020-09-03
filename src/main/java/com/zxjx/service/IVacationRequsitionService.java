package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.VacationRequsition;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:45
 */
public interface IVacationRequsitionService extends IBaseService<VacationRequsition> {
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
    String auditVacationRequsition(Long auditBy,Long vacationRequsitionId);

    /**
     * <p>
     * 撤销请假申请
     * </p>
     *
     * @param vacationRequsitionId
     * @return message
     * @author liuzhixiang 2020/05/11 22:18
     */
    String cancelVacationRequsition(Long vacationRequsitionId);
}
