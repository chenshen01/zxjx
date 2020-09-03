package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description 教师表
 *
 * @author liuzhixiang 2020/04/04 12:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_teacher")
public class Teacher implements Serializable {

    public static final String WAIT_AUDIT = "等待审批通过";
    public static final String AUDITED = "审批通过";
    public static final String ON_CLASS = "在上课";
    public static final String OFF_CLASS = "未上课";
    public static final String ON_VACATION = "休假中";
    public static final String ON_ADJUST_CLASS = "调课中";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 教工号/登录的账号
     */
    @Column(name = "teacher_code")
    private String teacherCode;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 头像路径
     */
    @Column(name = "head_image")
    private String headImage;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * qq
     */
    @Column(name = "qq")
    private String qq;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 曾任教地方
     */
    @Column(name = "teached_place")
    private String teachedPlace;

    /**
     * 简历
     */
    @Column(name = "resume")
    private String resume;

    /**
     * 状态 待审核通过、休假中、已调课、上课中、未上课
     */
    @Column(name = "status")
    private String status;

    /**
     * 性别 1是男生 0是女生
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 薪水
     */
    @Column(name = "salary")
    private BigDecimal salary;
}
