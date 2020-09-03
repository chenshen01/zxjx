package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.entity.Result;
import com.zxjx.entity.ShopCar;
import com.zxjx.service.IShopCarService;
import com.zxjx.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:42
 */
@RestController
public class ShopCarController {
    @Autowired
    private IShopCarService service;

    @GetMapping( value = "/zxjx/ShopCar/selectById/{id}")
    public ShopCar selectById(@PathVariable("id") Long id){
        return service.selectById(ShopCar.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/ShopCar/insert")
    public Result insert(ShopCar shopCar){
        shopCar.setCreateTime(DateUtil.getCurrentDate());
        shopCar.setStatus(ShopCar.SHOP_CAR_NEW);
        return Result.builder().message(BaseMassage.ADD_SHOPCAR_SUCCESS).code(Result.SUCCESS_CODE)
                .result(service.insert(shopCar)).build();
    }

    @PostMapping( value = "/zxjx/ShopCar/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(ShopCar.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/ShopCar/update")
    public int update(@RequestBody ShopCar shopCar){
        return service.updateByPrimaryKey(shopCar);
    }
}
