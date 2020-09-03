package com.zxjx.service.impl;

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.PlatForm;
import com.zxjx.service.IPlatFormService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlatFormServiceImpl extends BaseServiceImpl<PlatForm> implements IPlatFormService {

}
