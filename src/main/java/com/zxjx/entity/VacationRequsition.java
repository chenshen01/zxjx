package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description 休假申请
 *
 * @author liuzhixiang 2020/04/04 12:39
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_vacation_requsition")
public class VacationRequsition implements Serializable {

    public static final String VACATION_REQUSITION_PASS = "审批通过";
    public static final String VACATION_REQUSITION_WAIT = "等待审批";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 申请调课的人
     */
    @Column(name = "requisition_by")
    private Long requisitionBy;

    /**
     * 申请调课的人姓名
     */
    @Column(name = "requisition_name")
    private String requisitionName;

    /**
     * 申请休假的时间 从
     */
    @Column(name = "vacation_time_from")
    private Date vacationTimeFrom;

    /**
     * 申请休假的时间 到
     */
    @Column(name = "vacation_time_to")
    private Date vacationTimeTo;

    /**
     * 申请状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 审批的人
     */
    @Column(name = "audit_by")
    private Long auditBy;

    /**
     * 课程
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 审批的时间
     */
    @Column(name = "audit_time")
    private Date auditTime;

    /**
     * 角色
     */
    @Column(name = "role")
    private String role;
}
