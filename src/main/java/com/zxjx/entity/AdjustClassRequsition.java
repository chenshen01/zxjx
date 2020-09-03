package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description 调课申请
 *
 * @author liuzhixiang 2020/04/03 22:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_adjust_class_requsition")
public class AdjustClassRequsition implements Serializable {

    public static final String WAIT_AUDIT = "等待审批";
    public static final String PASS = "通过";
    public static final String REJECT = "拒绝";

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
     * 调整前的时间
     */
    @Column(name = "before_adjust_time")
    private Date beforeAdjustTime;

    /**
     * 调整的时间
     */
    @Column(name = "adjust_time")
    private Date adjustTime;

    /**
     * 状态：待审批、拒绝、通过
     */
    @Column(name = "status")
    private String status;

    /**
     * 审批的人
     */
    @Column(name = "audit_by")
    private Long auditBy;

    /**
     * 需要调整的课程
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 审批时间
     */
    @Column(name = "audit_time")
    private Date auditTime;
}
