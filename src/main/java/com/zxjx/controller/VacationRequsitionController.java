package com.zxjx.controller;

import com.zxjx.base.BaseConstant;
import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.entity.Teacher;
import com.zxjx.entity.VacationRequsition;
import com.zxjx.service.IStudentService;
import com.zxjx.service.ITeacherService;
import com.zxjx.service.IVacationRequsitionService;
import com.zxjx.utils.DateUtil;
import com.zxjx.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:46
 */
@RestController
public class VacationRequsitionController {

    @Autowired
    private IVacationRequsitionService service;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @GetMapping( value = "/zxjx/vacation-requsition/selectById/{id}")
    public VacationRequsition selectById(@PathVariable("id") Long id){

        return service.selectById(VacationRequsition.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/vacation-requsition/insert")
    public int insert(VacationRequsition vacationRequsition){
        return service.insert(vacationRequsition);
    }

    @PostMapping( value = "/zxjx/vacation-requsition/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return Result.builder().message(BaseMassage.BACK_SUCCESS).code(Result.SUCCESS_CODE).result(
                service.deleteByPrimaryKey(VacationRequsition.builder().id(id).build())).build();
    }

    @PostMapping(value = "/zxjx/vacation-requsition/update")
    public int update(@RequestBody VacationRequsition vacationRequsition){
        return service.updateByPrimaryKey(vacationRequsition);
    }

    @PostMapping(value = "/zxjx/vacation-requsition/createVacationRequsition")
    public Result createVacationRequsition(Long requisitionBy,String role,
                                           String vacationTimeFrom,String vacationTimeTo){
        Date vacationTimeFromDate = DateUtil.getSimpleDate(vacationTimeFrom);
        Date vacationTimeToDate = DateUtil.getSimpleDate(vacationTimeTo);
        String requisitionName = "";
        if (vacationTimeFromDate.compareTo(vacationTimeToDate) >= 0) {
            return Result.builder().code(Result.FAIL_CODE).message(BaseMassage.ADD_CREATE_VACATION_REQUSITION_FAIL)
                    .build();
        }
        if (BaseConstant.STUDENT.equals(role)) {
            Student student = studentService.selectById(Student.builder().id(requisitionBy).build());
            requisitionName = student.getName();
        } else {
            Teacher teacher = teacherService.selectById(Teacher.builder().id(requisitionBy).build());
            requisitionName = teacher.getName();
        }
        service.insert(VacationRequsition.builder().requisitionBy(requisitionBy).role(role).vacationTimeFrom(vacationTimeFromDate)
            .vacationTimeTo(vacationTimeToDate).status(VacationRequsition.VACATION_REQUSITION_WAIT).requisitionName(requisitionName).build());
        return Result.builder().code(Result.SUCCESS_CODE).message(BaseMassage.ADD_CREATE_VACATION_REQUSITION_SUCCESS)
            .build();
    }

    @GetMapping(value = "/zxjx/vacation-requsition/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                         @RequestParam(value = "requisitionBy",required = false) Long requisitionBy,
                                         @RequestParam(value = "role",required = false) String role,
                                         @RequestParam(value = "vacationTimeFrom",required = false) String vacationTimeFrom,
                                         @RequestParam(value = "vacationTimeTo",required = false) String vacationTimeTo,
                                         @RequestParam(value = "status",required = false) String status){

        VacationRequsition vacationRequsition = VacationRequsition.builder().build();
        vacationRequsition.setStatus(status);
        vacationRequsition.setRequisitionBy(requisitionBy);
        vacationRequsition.setRole(role);
        if (!StringUtils.isEmpity(vacationTimeFrom)) {
            vacationRequsition.setVacationTimeFrom(DateUtil.getSimpleDate(vacationTimeFrom));
        }
        if (!StringUtils.isEmpity(vacationTimeTo)) {
            vacationRequsition.setVacationTimeTo(DateUtil.getSimpleDate(vacationTimeTo));
        }
        return new Result(BaseMassage.QUERY_SUCCESS,service.selectPagesByCondition(PageRequest.builder().pageNum(pageNum)
                .pageSize(pageSize).build(), vacationRequsition),Result.SUCCESS_CODE);

    }

    @PostMapping(value = "/zxjx/vacation-requsition/audit")
    public Result audit(Long vacationRequsitionId,Long auditBy){
        String message = service.auditVacationRequsition(auditBy, vacationRequsitionId);
        return Result.builder().code(Result.SUCCESS_CODE).result(null).message(message).build();
    }

    @PostMapping(value = "/zxjx/vacation-requsition/cancle")
    public Result cancle(Long vacationRequsitionId){
        String message = service.cancelVacationRequsition(vacationRequsitionId);
        return Result.builder().code(Result.SUCCESS_CODE).result(null).message(message).build();
    }
}
