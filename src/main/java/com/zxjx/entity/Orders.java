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
 * description 订单
 *
 * @author liuzhixiang 2020/04/04 11:36
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "zxjx_orders")
public class Orders implements Serializable {

    public static final String PAY_SUCCESS = "支付成功";
    public static final String PAY_WAIT = "等待支付";
    public static final String PAY_FAILED = "支付失败";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 买家名称
     */
    @Column(name = "buyer_name")
    private String buyerName;

    /**
     * 买家电话
     */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /**
     * 订单状态
     */
    @Column(name = "order_status")
    private String orderStatus;

    /**
     * 总金额数
     */
    @Column(name = "money_account")
    private BigDecimal moneyAccount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 支付状态
     */
    @Column(name = "payment_status")
    private String paymentStatus;

    /**
     * 支付人id
     */
    @Column(name = "buyer_id")
    private Long buyerId;
}
