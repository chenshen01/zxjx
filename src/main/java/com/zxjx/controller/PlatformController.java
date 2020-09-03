package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.redis.RedisCache;
import com.zxjx.entity.PlatForm;
import com.zxjx.entity.Result;
import com.zxjx.service.IPlatFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:58
 */
@RestController
public class PlatformController {

    @Autowired
    private IPlatFormService service;

    @GetMapping( value = "/zxjx/platform/selectById/{id}")
    public PlatForm selectById(@PathVariable("id") Long id){

        return service.selectById(PlatForm.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/platform/insert")
    public int insert(PlatForm platForm){
        return service.insert(platForm);
    }

    @PostMapping( value = "/zxjx/platform/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(PlatForm.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/platform/update")
    public int update(@RequestBody PlatForm platForm){
        return service.updateByPrimaryKey(platForm);
    }

    @RedisCache(key = "zxjx:platform",resultType = PlatForm.class)
    @GetMapping(value = "/zxjx/platform/selectAll")
    public Result selectAll(){
        return new Result(BaseMassage.QUERY_SUCCESS,service.selectAll(PlatForm.builder().build()).get(0)
                ,Result.SUCCESS_CODE);
    }
}
