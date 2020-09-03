package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * description 班级
 *
 * @author liuzhixiang 2020/04/03 23:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_classes")
public class Classes implements Serializable {

    public static final String CLASS_ON = "上课中";
    public static final String CLASS_OFF = "未上课";
    public static final String CLASS_NEW = "新建";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 班级代码 唯一
     */
    @Column(name = "class_code")
    private String classCode;

    /**
     * 班级容量
     */
    @Column(name = "class_capacity")
    private Integer classCapacity;

    /**
     * 班级状态（在上课，未上课，新建）
     */
    @Column(name = "class_status")
    private String classStatus;

    /**
     * 已有学生人数
     */
    @Column(name = "student_account")
    private Integer studentAccount;

    /**
     * 课程id
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 班级图片
     */
    @Column(name = "class_image")
    private String classImage;
}
