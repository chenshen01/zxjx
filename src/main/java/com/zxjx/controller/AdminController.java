package com.zxjx.controller;

import com.zxjx.entity.Admin;
import com.zxjx.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:42
 */
@RestController
public class AdminController {
    @Autowired
    private IAdminService service;

    @GetMapping( value = "/zxjx/admin/selectById/{id}")
    public Admin selectById(@PathVariable("id") Long id){

        return service.selectById(Admin.builder().id(id).build());
    }


    @PostMapping(value = "/zxjx/admin/insert")
    public int insert(Admin admin){
        return service.insert(admin);
    }

    @PostMapping( value = "/zxjx/admin/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(Admin.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/admin/update")
    public int update(@RequestBody Admin admin){
        return service.updateByPrimaryKey(admin);
    }
}
