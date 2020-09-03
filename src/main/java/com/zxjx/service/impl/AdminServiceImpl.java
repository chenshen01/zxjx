package com.zxjx.service.impl;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:40
 */

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Admin;
import com.zxjx.service.IAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements IAdminService {

}
