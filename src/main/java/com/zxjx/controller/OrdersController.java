package com.zxjx.controller;

import com.zxjx.entity.Orders;
import com.zxjx.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:49
 */
@RestController
public class OrdersController {
    @Autowired
    private IOrdersService service;

    @GetMapping( value = "/zxjx/orders/selectById/{id}")
    public Orders selectById(@PathVariable("id") Long id){

        return service.selectById(Orders.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/orders/insert")
    public int insert(Orders orders){
        return service.insert(orders);
    }

    @PostMapping( value = "/zxjx/orders/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(Orders.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/orders/update")
    public int update(@RequestBody Orders orders){
        return service.updateByPrimaryKey(orders);
    }
}
