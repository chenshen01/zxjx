package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.entity.Classes;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 23:07
 */
@RestController
public class ClassesController {
    @Autowired
    private IClassesService service;
    @GetMapping( value = "/zxjx/classes/selectById/{id}")
    public Classes selectById(@PathVariable("id") Long id){

        return service.selectById(Classes.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/classes/insert")
    public int insert(Classes classes){
        classes.setClassStatus(Classes.CLASS_NEW);
        return service.insert(classes);
    }

    @PostMapping( value = "/zxjx/classes/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        String message = service.deleteClass(id);
        return Result.builder().result(null).code(Result.SUCCESS_CODE).message(message)
                .build();
    }
    @PostMapping(value = "/zxjx/classes/update")
    public int update(@RequestBody Classes classes){
        return service.updateByPrimaryKey(classes);
    }

    @GetMapping(value = "/zxjx/classes/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                                         @RequestParam(value = "classCode",required = false) String classCode,
                                         @RequestParam(value = "classStatus",required = false) String classStatus){

        return new Result(BaseMassage.QUERY_SUCCESS,service.selectPagesByCondition(PageRequest.builder().pageNum(pageNum)
                .pageSize(pageSize).build(), Classes.builder().classCode(classCode).classStatus(classStatus).build()), Result.SUCCESS_CODE);
    }

    @PostMapping(value = "/zxjx/classes/addClasses")
    public Result addClasses(HttpServletRequest request,Long courseId,String classCode,
                             Integer classCapacity,@RequestParam("classImage") MultipartFile classImage){
        String message = service.addClasses(request, courseId, classCode, classCapacity, classImage);
        return Result.builder().code(Result.SUCCESS_CODE).result(null).message(message).build();
    }
}
