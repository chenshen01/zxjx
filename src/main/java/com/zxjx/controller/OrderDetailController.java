package com.zxjx.controller;

import com.zxjx.entity.OrderDetail;
import com.zxjx.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:34
 */
@RestController
public class OrderDetailController {

    @Autowired
    private IOrderDetailService service;

    @GetMapping( value = "/zxjx/order-detail/selectById/{id}")
    public OrderDetail selectById(@PathVariable("id") Long id){

        return service.selectById(OrderDetail.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/order-detail/insert")
    public int insert(OrderDetail orderDetail){
        return service.insert(orderDetail);
    }

    @PostMapping( value = "/zxjx/order-detail/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(OrderDetail.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/order-detail/update")
    public int update(@RequestBody OrderDetail orderDetail){
        return service.updateByPrimaryKey(orderDetail);
    }
}
