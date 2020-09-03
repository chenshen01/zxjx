package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.AdjustClassRequsition;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:24
 */
public interface IAdjustClassRequsitionService extends IBaseService<AdjustClassRequsition> {

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
    String auditAdjustClassRequsition(Long adjustClassRequsitionId,Long auditBy);

   // String createAdjustClassRequsition();

    /**
     * <p>
     *撤回调课申请
     * </p>
     *
     * @param adjustClassRequsitionId
     * @return message
     * @author liuzhixiang 2020/05/12 10:59
     */
    String cancelAdjustClassRequsition(Long adjustClassRequsitionId);
}
