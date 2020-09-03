package com.zxjx.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * description 订单明细
 *
 * @author liuzhixiang 2020/04/04 11:25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_order_detail")
public class    OrderDetail implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 课程id
     */
    @Column(name = "course_id")
    private Long courseId;

    /**
     * 订单编号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 课程照片
     */
    @Column(name = "course_image")
    private String courseImage;

    /**
     * 课程价格
     */
    @Column(name = "coursePrice")
    private BigDecimal coursePrice;

    /**
     * 课程名字
     */
    @Column(name = "course_name")
    private String courseName;
}
