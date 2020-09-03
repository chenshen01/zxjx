package com.zxjx.service.impl;

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Orders;
import com.zxjx.service.IOrdersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements IOrdersService {

}
