package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.base.basePage.PageResult;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.entity.Teacher;
import com.zxjx.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:37
 */
@RestController
public class TeacherController {

    @Autowired
    private ITeacherService service;

    @GetMapping( value = "/zxjx/teacher/selectById/{id}")
    public Teacher selectById(@PathVariable("id") Long id){

        return service.selectById(Teacher.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/teacher/insert")
    public int insert(Teacher teacher){
        return service.insert(teacher);
    }

    @PostMapping( value = "/zxjx/teacher/delete/{id}")
    public int delete(@PathVariable("id") Long id){
        return service.deleteByPrimaryKey(Teacher.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/teacher/update")
    public int update(@RequestBody Teacher teacher){
        return service.updateByPrimaryKey(teacher);
    }

    @GetMapping(value = "/zxjx/teacher/selectByCondition")
    public List<Teacher> selectByCondition(@RequestBody Teacher teacher){
        return service.selectByCondition(teacher);
    }

    @GetMapping(value = "/zxjx/teacher/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                         @RequestParam(value = "name",required = false) String name,
                                         @RequestParam(value = "nickName",required = false) String nickName,
                                         @RequestParam(value = "studyCode",required = false) String teacherCode,
                                         @RequestParam(value = "onlineStatus",required = false) String status){

        return new Result(BaseMassage.QUERY_SUCCESS,service.selectPagesByCondition(PageRequest.builder().pageNum(pageNum)
                .pageSize(pageSize).build(), Teacher.builder().name(name).teacherCode(teacherCode).status(status)
                .nickName(nickName).build()),Result.SUCCESS_CODE);
    }

    @PostMapping(value = "/zxjx/teacher/auditPass")
    public Result auditPass(HttpServletRequest request,Long id){
        String message = service.auditPass(request, id);
        return new Result(message,null,Result.SUCCESS_CODE);
    }

    @PostMapping(value = "/zxjx/teacher/sendSalary")
    public Result sendSalary(HttpServletRequest request, Long id, BigDecimal salary){
        String message = service.sentSalary(request,id,salary);
        return new Result(message,null,Result.SUCCESS_CODE);
    }

    @GetMapping(value = "/zxjx/teacher/queryPagesTeacherTeachingClasses")
    public Result queryPagesTeacherTeachingClasses( @RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                                    @RequestParam(value = "pageSize",defaultValue = "8") int pageSize,
                                                    HttpServletRequest request, Long teacherId){
        PageResult pageResult = service.queryPagesTeacherTeachingClasses(request, PageRequest.builder().pageNum(pageNum).pageSize(pageSize).build(),
                teacherId);
        if (pageResult != null) {
            return Result.builder().message(BaseMassage.QUERY_SUCCESS).code(Result.SUCCESS_CODE).result(pageResult).build();
        } else {
            return Result.builder().message(BaseMassage.QUERY_FAIL).code(Result.FAIL_CODE).result(pageResult).build();
        }
    }
}
