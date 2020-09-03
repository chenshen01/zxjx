package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.entity.CourseClass;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.service.ICourseClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/02 16:43
 */
@RestController
public class CourseClassController {
    @Autowired
    private ICourseClassService service;

    @GetMapping( value = "/zxjx/course-class/selectById/{id}")
    public CourseClass selectById(@PathVariable("id") Long id){

        return service.selectById(CourseClass.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/course-class/insert")
    public Result insert(CourseClass courseClass){
        List<CourseClass> courseClasses = service.selectByCondition(courseClass);
        if (courseClasses != null && !courseClasses.isEmpty()) {
            return Result.builder().message(BaseMassage.ADD_COURSE_CLASS_DUP).result(null).code(Result.FAIL_CODE).build();
        } else {
            return Result.builder().message(BaseMassage.ADD_COURSE_CLASS_SUCCESS).result(service.insert(courseClass)).
                    code(Result.SUCCESS_CODE).build();
        }
    }

    @PostMapping( value = "/zxjx/course-class/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(CourseClass.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/course-class/update")
    public Result update(@RequestBody CourseClass courseClass){
        return new Result(BaseMassage.UPDATE_SUCCESS,service.updateByPrimaryKey(courseClass),Result.SUCCESS_CODE);
    }

    @GetMapping(value = "/zxjx/course-class/selectByCondition")
    public List<CourseClass> selectByCondition(@RequestBody CourseClass courseClass){
        return service.selectByCondition(courseClass);
    }

    @GetMapping(value = "/zxjx/course-class/selectAll")
    public Result selectAll(){
        return Result.builder().code(Result.SUCCESS_CODE).message(BaseMassage.QUERY_SUCCESS)
                .result(service.selectAll(CourseClass.builder().build())).build();
    }

    @GetMapping(value = "/zxjx/course-class/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name",required = false) String courseClassName){

        return new Result(BaseMassage.QUERY_SUCCESS,service.selectPagesByCondition(PageRequest.builder().pageNum(pageNum)
                .pageSize(pageSize).build(), CourseClass.builder().courseClassName(courseClassName).build()),Result.SUCCESS_CODE);
    }

    @GetMapping(value = "/zxjx/course-class/queryAllCourseClass")
    public Result queryAllCourseClass(){
        return Result.builder().code(Result.SUCCESS_CODE).message(BaseMassage.QUERY_SUCCESS)
                .result(service.queryAllCourseClass()).build();
    }
}
