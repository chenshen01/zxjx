package com.zxjx.controller;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.basePage.PageRequest;
import com.zxjx.entity.Courses;
import com.zxjx.entity.Result;
import com.zxjx.entity.Teacher;
import com.zxjx.service.IAttachmentService;
import com.zxjx.service.ICoursesService;
import com.zxjx.service.ITeacherService;
import com.zxjx.utils.DateUtil;
import com.zxjx.component.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 11:22
 */
@RestController
public class CourseController {

    @Value("${file.course-image-location}")
    private String courseImageLocation;

    @Autowired
    private ICoursesService service;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private MailUtil mailUtil;

    @GetMapping( value = "/zxjx/courses/selectById/{id}")
    public Courses selectById(@PathVariable("id") Long id){
        return service.selectById(Courses.builder().id(id).build());
    }

    @PostMapping(value = "/zxjx/courses/insert")
    public int insert(Courses courses){
        return service.insert(courses);
    }

    @PostMapping( value = "/zxjx/courses/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return new Result(BaseMassage.DELETE_SUCCESS,service.deleteByPrimaryKey(Courses.builder().id(id).build()),
                Result.SUCCESS_CODE);
    }

    @PostMapping(value = "/zxjx/courses/update")
    public int update(@RequestBody Courses courses){
        return service.updateByPrimaryKey(courses);
    }

    @GetMapping(value =  "/zxjx/courses/selectByCondition")
    public Result selectByCondition(HttpServletRequest request,Courses courses){
        HttpSession session = request.getSession();
        return Result.builder().code(Result.SUCCESS_CODE).result(service.selectByCondition(courses))
                .message(BaseMassage.QUERY_SUCCESS).build();
    }

    @PostMapping(value = "/zxjx/courses/addCourses")
    public Result addCourses(HttpServletRequest request, Long teachedBy, String courseName,
                             Long courseClass, BigDecimal price,Integer courseTime,
                             String timeQuantum,String description, String courseStart,String courseEnd,
                             @RequestParam("courseImage") MultipartFile courseImage,String teacherName){
        // 1.处理课程封面
        String courseImageUrl = attachmentService.upload(courseImage,request,courseImageLocation);
        // 2.添加课程
        Date courseStartDate = DateUtil.getSimpleDate(courseStart);
        Date courseEndDate = DateUtil.getSimpleDate(courseEnd);
        if (courseStartDate.after(courseEndDate)) {
            return new Result(BaseMassage.ADD_COURSE_FAIL_TIME,null, Result.FAIL_CODE);
        }
        int insert = service.insert(Courses.builder().teachedBy(teachedBy).courseClass(courseClass).courseName(courseName)
                .courseImage(courseImageUrl).courseTime(courseTime).description(description).price(price).timeQuantum(timeQuantum)
                .status(Courses.COURSE_STATUS_NEW).teacherName(teacherName).courseStart(courseStartDate).courseEnd(courseEndDate)
                .build());
        if (insert > 0) {
            // 3.给老师发邮件
            String email = teacherService.selectById(Teacher.builder().id(teachedBy).build()).getEmail();
            mailUtil.sendMail(email,BaseMassage.SUBJECT,"您已成功创建" + courseName + "课程，请耐心等待管理员审核！");
            return new Result(BaseMassage.ADD_COURSE_SUCCESS,null, Result.SUCCESS_CODE);
        } else {
            return new Result(BaseMassage.ADD_COURSE_FAIL,null, Result.FAIL_CODE);
        }

    }

    @GetMapping(value = "/zxjx/courses/selectPagesByCondition")
    public Result selectPagesByCondition(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum ,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "courseClass",required = false) Long courseClass,
                                         @RequestParam(value = "courseName",required = false) String courseName,
                                         @RequestParam(value = "teachedBy",required = false) Long teachedBy,
                                         @RequestParam(value = "status",required = false) String status){

        return Result.builder().code(Result.SUCCESS_CODE).result(service.selectPagesByCondition(PageRequest.builder()
                .pageSize(pageSize).pageNum(pageNum).build(),Courses.builder().courseClass(courseClass).courseName(courseName)
                .teachedBy(teachedBy).status(status).build())).message(BaseMassage.QUERY_SUCCESS).build();
    }

    @PostMapping(value = "/zxjx/courses/auditCourse")
    public Result auditCourse(HttpServletRequest request,Long id){
        String auditCourse = service.auditCourse(request, id);
        return new Result(auditCourse,null,Result.SUCCESS_CODE);
    }

    @PostMapping(value = "/zxjx/courses/rejectCourse")
    public Result rejectCourse(HttpServletRequest request,Long id){
        String rejectCourse = service.rejectCourse(request, id);
        return new Result(rejectCourse,null,Result.SUCCESS_CODE);
    }
}
