package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 管理员
 *
 * @author liuzhixiang 2020/04/03 22:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 管理员账号
     */
    @Column(name = "admin_code")
    private String adminCode;

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
}
