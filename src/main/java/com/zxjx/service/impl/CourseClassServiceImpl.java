package com.zxjx.service.impl;

import com.zxjx.base.BaseServiceImpl;
import com.zxjx.base.redis.RedisCache;
import com.zxjx.entity.CourseClass;
import com.zxjx.mapper.CourseClassMapper;
import com.zxjx.service.ICourseClassService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:37
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseClassServiceImpl  extends BaseServiceImpl<CourseClass> implements ICourseClassService{
    @Autowired
    private CourseClassMapper mapper;

    @RedisCache(key = "zxjx:courseClass",resultType = CourseClass.class)
    @Override
    public List<CourseClass> queryAllCourseClass() {
        return  mapper.selectAll(CourseClass.builder().build());
    }
}
