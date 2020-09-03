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
 * description 课程
 *
 * @author liuzhixiang 2020/04/04 11:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_courses")
public class Courses implements Serializable {

    public static final String COURSE_STATUS_NEW = "新建";
    public static final String COURSE_STATUS_AUDITED = "已审核";
    public static final String COURSE_STATUS_REJECTED = "已拒绝";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 课程所属类别
     */
    @Column(name = "course_class")
    private Long courseClass;

    /**
     * 课程名称
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * 任课老师
     */
    @Column(name = "teached_by")
    private Long teachedBy;

    /**
     * 价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 课程描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 课程时间段
     */
    @Column(name = "time_quantum")
    private String timeQuantum;

    /**
     * 课程照片
     */
    @Column(name = "course_image")
    private String courseImage;

    /**
     * 课时
     */
    @Column(name = "course_time")
    private Integer courseTime;

    /**
     * 多少人选了这个课
     */
    @Column(name = "studied_account")
    private Integer studiedAccount;

    /**
     * 课程评分
     */
    @Column(name = "comprehensive_score")
    private BigDecimal comprehensiveScore;

    /**
     * 审核通过 已拒绝 新建
     */
    @Column(name = "status")
    private String status;

    /**
     * 教师名称
     */
    @Column(name = "teacher_name")
    private String teacherName;

    /**
     * 课程开始时间
     */
    @Column(name = "course_start")
    private Date courseStart;

    /**
     * 课程结束时间
     */
    @Column(name = "course_end")
    private Date courseEnd;
}
