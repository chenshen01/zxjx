package com.zxjx.controller;

import com.zxjx.entity.*;
import com.zxjx.service.ICourseClassService;
import com.zxjx.service.ICoursesService;
import com.zxjx.service.IStudentService;
import com.zxjx.service.ITeacherService;
import com.zxjx.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 15:53
 */
@Controller
public class PageController {
    @Autowired
    private ICoursesService coursesService;

    @Autowired
    private ICourseClassService courseClassService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IStudentService studentService;

    @RequestMapping(value = "/zxjx/index1",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        // 查询所有课程分类
        List<CourseClass> courseClasses = courseClassService.selectAll(CourseClass.builder().build());
        // 查询所有课程
        // List<Courses> courses = coursesService.selectAll(Courses.builder().build());
        modelAndView.addObject("courseClasses",courseClasses);
        // 查询所有的课程

        return modelAndView;
    }

    //--------------------------------------通用 登录 和 注册-------------------------//

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(){
        return "common/login";
    }

    @RequestMapping(value = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    public String register(){
        return "common/register";
    }

    //-------------------------------------- 管理员 -------------------------//

    @RequestMapping(value = "/admin",method = {RequestMethod.GET,RequestMethod.POST})
    public String admin(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        Admin curAdmin = (Admin)request.getSession().getAttribute("curAdmin");
        return "admin/admin";
    }

    @RequestMapping(value = "/admin/getTeacherDetail",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView adminTeacherDetail(HttpServletRequest request,Long id){
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/teacherDetail");
        model.addObject("teacher",teacherService.selectById(Teacher.builder().id(id).build()));
        return model;
    }

    @RequestMapping(value = "/admin/getStudentDetail",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView adminStudentDetail(HttpServletRequest request,Long id){
        ModelAndView model = new ModelAndView();
        model.setViewName("admin/studentDetail");
        Student student = studentService.selectById(Student.builder().id(id).build());
        student.setHeadImage(FileUtil.getRelativeLocation(student.getHeadImage()));
        model.addObject("student",student);
        return model;
    }

    @RequestMapping(value = "/admin/backToAdmin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result backToAdmin(HttpServletRequest request, HttpServletResponse response){
        return Result.builder().code(Result.SUCCESS_CODE).build();
    }

    @RequestMapping(value = "/admin/addClasses",method = {RequestMethod.GET,RequestMethod.POST})
    public String addClasses(HttpServletRequest request){
        return "admin/addClasses";
    }
    //-------------------------------------- 老师 -------------------------//

    @RequestMapping(value = "/teacher/getTeacherMain",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getTeacherMain(HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        ModelAndView model = new ModelAndView();
        model.setViewName("teacher/teacherMain");
        Teacher curTeacher = (Teacher)request.getSession().getAttribute("curTeacher");
        model.addObject("teacher",curTeacher);
        return model;
    }

    @RequestMapping(value = "/teacher/teacherAddCourse",method = {RequestMethod.GET,RequestMethod.POST})
    public String teacherAddCourse(){
        return "teacher/teacherAddCourse";
    }

    @RequestMapping(value = "/teacher/backToGetTeacherMain",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result backToGetTeacherMain(HttpServletRequest request, HttpServletResponse response){
        return Result.builder().code(Result.SUCCESS_CODE).build();
    }


    //-------------------------------------- 主页 -------------------------//
    @RequestMapping(value = "/main/backToMain",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result backToMain(HttpServletRequest request, HttpServletResponse response){
        return Result.builder().code(Result.SUCCESS_CODE).build();
    }

    @RequestMapping(value = "/main",method = {RequestMethod.GET,RequestMethod.POST})
    public String index1(HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        return "common/main";
    }

    //-------------------------------------- 学生 -------------------------//

    @RequestMapping(value = "/student/getStudentMain",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getStudentMain(HttpServletRequest request,HttpServletResponse response){
        // 解决跨域
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        ModelAndView model = new ModelAndView();
        model.setViewName("student/studentMain");
        Student curStudent = (Student)request.getSession().getAttribute("curStudent");
        model.addObject("student",curStudent);
        return model;
    }

    //-------------------------------------- 课程 -------------------------//

    @RequestMapping(value = "/courses/getCourseDetail",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getCourseDetail(HttpServletRequest request, HttpServletResponse response, Long id,
                                        @RequestParam(name = "studentId",required = false) Long studentId){
        // 解决跨域
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        ModelAndView model = new ModelAndView();
        model.setViewName("course/courseDetail");
        Student curStudent = (Student)request.getSession().getAttribute("curStudent");
        if (curStudent == null) {
            if (studentId != null) {
                curStudent = studentService.selectById(Student.builder().id(studentId).build());
            }
        }
        model.addObject("student",curStudent);
        Courses courses = coursesService.selectById(Courses.builder().id(id).build());
        model.addObject("courses",courses);
        return model;
    }

    //-------------------------------------- 请假 -------------------------//

    @RequestMapping(value = "/common/createVacation",method = {RequestMethod.GET,RequestMethod.POST})
    public String createVacation(){
        return "common/createVacation";
    }
}
