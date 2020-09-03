package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description 平台基本数据
 *
 * @author liuzhixiang 2020/04/04 11:51
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_platform")
public class PlatForm implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 平台总收益
     */
    @Column(name = "account")
    private BigDecimal account;

    /**
     * 平台总学生数
     */
    @Column(name = "sum_student")
    private Integer sumStudent;

    /**
     * 平台总课程数
     */
    @Column(name = "sum_course")
    private Integer sumCourse;

    /**
     * 平台总教师数
     */
    @Column(name = "sum_teacher")
    private Integer sumTeacher;

    /**
     * 平台总班级数
     */
    @Column(name = "sum_classes")
    private Integer sumClasses;
}
