package com.zxjx.controller;

import com.zxjx.entity.AdjustClassRequsition;
import com.zxjx.entity.CourseClass;
import com.zxjx.service.IAdjustClassRequsitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:27
 */
@RestController
@Api("调课管理")
public class AdjustClassRequsitionController {
    @Autowired
    private IAdjustClassRequsitionService service;

    @ApiOperation(value = "根据id查询调课信息", notes = "查询数据库中某个的调课信息")
    @ApiImplicitParam(name = "cid", value = "客户id", required = true, dataType = "Long")
    @GetMapping( value = "/zxjx/adjust-class-requsition/selectById/{id}")
    public AdjustClassRequsition selectById(@PathVariable("id") Long id){

        return service.selectById(AdjustClassRequsition.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/adjust-class-requsition/insert")
    public int insert(AdjustClassRequsition adjustClassRequsition){
        return service.insert(adjustClassRequsition);
    }

    @PostMapping( value = "/zxjx/adjust-class-requsition/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(AdjustClassRequsition.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/adjust-class-requsition/update")
    public int update(@RequestBody AdjustClassRequsition adjustClassRequsition){
        return service.updateByPrimaryKey(adjustClassRequsition);
    }
}
