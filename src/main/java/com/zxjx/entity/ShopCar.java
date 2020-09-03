package com.zxjx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * description 购物车
 *
 * @author liuzhixiang 2020/04/25 21:26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_shop_car")
public class ShopCar implements Serializable {

    public static final String SHOP_CAR_NEW = "新建";
    public static final String SHOP_CAR_PAID = "已支付";

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
     * 学生id
     */
    @Column(name = "student_id")
    private Long studentId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;
}
