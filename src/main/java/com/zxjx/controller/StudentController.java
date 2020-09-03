package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.redis.RedisCache;
import com.zxjx.entity.CourseClass;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 20:05
 */
@RestController
public class StudentController {

    @Autowired
    private IStudentService service;

    @RedisCache(key = "zxjx:student",resultType = Student.class)
    @GetMapping( value = "/zxjx/student/selectById/{id}")
    public Result selectById(@PathVariable("id") Long id){

        return Result.builder().result(service.selectById(Student.builder().id(id).build())).build();
    }

    @PostMapping(value = "/zxjx/student/insert")
    public int insert(Student student){
        return service.insert(student);
    }

    @PostMapping( value = "/zxjx/student/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(Student.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/student/update")
    public int update(@RequestBody Student student){
        return service.updateByPrimaryKey(student);
    }

    @GetMapping(value = "/zxjx/student/selectByCondition")
    public List<Student> selectByCondition(@RequestBody Student student){
        return service.selectByCondition(student);
    }

    @GetMapping(value = "/zxjx/student/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                         @RequestParam(value = "name",required = false) String name,
                                         @RequestParam(value = "nickName",required = false) String nickName,
                                         @RequestParam(value = "studyCode",required = false) String studyCode,
                                         @RequestParam(value = "onlineStatus",required = false) String onlineStatus){

        return new Result(BaseMassage.QUERY_SUCCESS,service.selectPagesByCondition(PageRequest.builder().pageNum(pageNum)
                .pageSize(pageSize).build(), Student.builder().name(name).studyCode(studyCode).onlineStatus(onlineStatus)
                .nickName(nickName).build()),Result.SUCCESS_CODE);
    }
}
