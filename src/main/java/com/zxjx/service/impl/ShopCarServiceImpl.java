package com.zxjx.service.impl;

/**
 * description 购物车
 *
 * @author liuzhixiang 2020/04/03 22:40
 */

import com.zxjx.entity.ShopCar;
import com.zxjx.service.IShopCarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zxjx.base.BaseServiceImpl;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopCarServiceImpl extends BaseServiceImpl<ShopCar> implements IShopCarService {

}
