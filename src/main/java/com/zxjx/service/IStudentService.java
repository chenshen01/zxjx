package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.Student;

import java.math.BigDecimal;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 19:53
 */
public interface IStudentService extends IBaseService<Student> {

    /**
     * <p>
     *购买课程
     * </p>
     *
     * @param courseIds  购买的课程id
     * @param studentId 学生id
     * @param amount  总金额
     * @param payType 支付方式
     * @return message
     * @author liuzhixiang 2020/05/04 18:39
     */
    String buyCourses(Long studentId, BigDecimal amount, List<Long> courseIds,String payType);
}
