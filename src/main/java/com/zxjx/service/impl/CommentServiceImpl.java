package com.zxjx.service.impl;

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Comment;
import com.zxjx.service.ICommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements ICommentService {

}
