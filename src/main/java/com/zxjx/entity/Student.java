package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 19:33
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_student")
public class Student implements Serializable {
    /**
     * 在线状态
     */
    public static final String ON_LINE = "在线";
    public static final String OFF_LINE = "离线";
    public static final String ON_VACATION = "休假中";
    public static final String ON_ADJUST = "已调课";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 学号/登录的账号
     */
    @Column(name = "study_code")
    private String studyCode;

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
     * 性别 1是男生 0是女生
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 所在院校
     */
    @Column(name = "school")
    private String school;

    /**
     * 账户
     */
    @Column(name = "account")
    private BigDecimal account;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 在线状态
     */
    @Column(name = "online_status")
    private String onlineStatus;
}
