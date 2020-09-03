package com.zxjx.service.impl;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:33
 */

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.OrderDetail;
import com.zxjx.service.IOrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements IOrderDetailService {

}
