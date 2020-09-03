package com.zxjx.controller;

import com.zxjx.entity.Result;
import com.zxjx.entity.SelectCourse;
import com.zxjx.entity.Student;
import com.zxjx.service.ISelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:18
 */
@RestController
public class SelectCourseController {
    @Autowired
    private ISelectCourseService service;

    @GetMapping( value = "/zxjx/select-course/selectById/{id}")
    public SelectCourse selectById(@PathVariable("id") Long id){

        return service.selectById(SelectCourse.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/select-course/insert")
    public int insert(SelectCourse selectCourse){
        return service.insert(selectCourse);
    }

    @PostMapping( value = "/zxjx/select-course/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(SelectCourse.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/select-course/update")
    public int update(@RequestBody SelectCourse selectCourse){
        return service.updateByPrimaryKey(selectCourse);
    }

    @PostMapping(value = "/zxjx/select-course/selectCourse")
    public Result selectCourse(Long studentId,Long classId,Long courseId){
        return Result.builder().message(service.selectClass(studentId, courseId, classId))
            .code(Result.SUCCESS_CODE).result(null).build();
    }
}
