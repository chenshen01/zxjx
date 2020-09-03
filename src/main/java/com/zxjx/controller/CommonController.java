package com.zxjx.controller;

import com.zxjx.base.BaseConstant;
import com.zxjx.base.BaseMassage;
import com.zxjx.entity.Admin;
import com.zxjx.entity.Result;
import com.zxjx.entity.Student;
import com.zxjx.entity.Teacher;
import com.zxjx.service.IAdminService;
import com.zxjx.service.IAttachmentService;
import com.zxjx.service.IStudentService;
import com.zxjx.service.ITeacherService;
import com.zxjx.component.CodeUtil;
import com.zxjx.component.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author liuzhixiang 2020/04/05 12:44
 */
@Slf4j
@Controller
public class CommonController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private CodeUtil codeUtil;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private MailUtil mailUtil;

    @RequestMapping(value = "/zxjx/login",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result login(HttpServletRequest request, @RequestBody Map<String,Object> loginInfo){
        ModelAndView model = new ModelAndView();
        String role = String.valueOf(loginInfo.get("role"));
        String code = String.valueOf(loginInfo.get("code"));
        String password = String.valueOf(loginInfo.get("password"));
        if (BaseConstant.ADMIN.equals(role)) {
            List<Admin> admins = adminService.selectByCondition(Admin.builder().adminCode(code).password(password)
                    .build());
            if (admins != null && !admins.isEmpty()) {
                //model.addObject("curAdmin",admins.get(0));
                request.getSession().setAttribute("curAdmin",admins.get(0));
                return new Result(BaseMassage.LOGIN_SUCCESS,BaseConstant.ADMIN,Result.SUCCESS_CODE);
            } else {
                return new Result(BaseMassage.LOGIN_ERROR_1,BaseConstant.ADMIN,Result.FAIL_CODE);
            }
        } else if (BaseConstant.STUDENT.equals(role)) {
            List<Student> students = studentService.selectByCondition(Student.builder().studyCode(code)
                    .password(password).build());
            if (students != null && !students.isEmpty()) {
                // 进入学生主页
                //model.addObject("curStudent",students.get(0));
                request.getSession().setAttribute("curStudent",students.get(0));
                return new Result(BaseMassage.LOGIN_SUCCESS,BaseConstant.STUDENT,Result.SUCCESS_CODE);
            } else {
                return new Result(BaseMassage.LOGIN_ERROR_1,BaseConstant.STUDENT,Result.FAIL_CODE);
            }
        } else if (BaseConstant.TEACHER.equals(role)) {
            List<Teacher> teachers = teacherService.selectByCondition(Teacher.builder().teacherCode(code)
                    .password(password).build());
            if (teachers != null && !teachers.isEmpty()) {
                Teacher teacher = teachers.get(0);
                // 教师注册成功，但是还等待管理员审核
                if (Teacher.WAIT_AUDIT.equals(teacher.getStatus())) {
                    return new Result(BaseMassage.WAIT_AUDIT,BaseConstant.TEACHER,Result.WAIT_CODE);
                } else {
                    // 进入教师主页
                    // model.addObject("curTeacher",teacher);
                    request.getSession().setAttribute("curTeacher",teacher);
                    return new Result(BaseMassage.LOGIN_SUCCESS,BaseConstant.TEACHER,Result.SUCCESS_CODE);
                }
            } else {
                model.addObject("massage", BaseMassage.LOGIN_ERROR_1);
                return new Result(BaseMassage.LOGIN_ERROR_1,BaseConstant.TEACHER,Result.FAIL_CODE);
            }
        }
        return new Result();
    }

    @RequestMapping(value = "/zxjx/student/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result studentRegister(HttpServletRequest request, String name,String nickName,
                                 String phone,String qq,String email,String password,String school,
                                 Integer gender,  @RequestParam("headImage") MultipartFile file,String role){
        // 1.处理头像
        String headImageUrl = attachmentService.uploadHeadImage(file, request);
        // 2.注册
        String studyCode = codeUtil.getStudyCode();
        studentService.insert(Student.builder().password(password).studyCode(studyCode).email(email).gender(gender)
                .headImage(headImageUrl).name(name).nickName(nickName).phone(phone).school(school).qq(qq).build());
        // 3.给学生发邮件
        mailUtil.sendMail(email,"骏波在线教育","您已注册成功，学号为" + studyCode + " 快去登录吧！");
        return new Result(BaseMassage.REGISTER_SUCCESS,null,Result.SUCCESS_CODE);
    }

    @RequestMapping(value = "/zxjx/teacher/register",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result teacherRegister(HttpServletRequest request, HttpServletResponse response, String name, String nickName,
                                  String phone, String qq, String email, String password, String teachedPlace,
                                  Integer gender, @RequestParam("headImage") MultipartFile file, @RequestParam("resume") MultipartFile resume){
        ModelAndView model = new ModelAndView();
        // 1.处理头像
        String headImageUrl = attachmentService.uploadHeadImage(file, request);
        // 2.处理简历
        String resumeUrl = attachmentService.uploadResume(resume, request);
        // 3.注册
        String teacherCode = codeUtil.getTeacherCode();
        teacherService.insert(Teacher.builder().password(password).teacherCode(teacherCode).email(email).gender(gender)
                .headImage(headImageUrl).name(name).nickName(nickName).phone(phone).qq(qq).resume(resumeUrl)
                .status(Teacher.WAIT_AUDIT).teachedPlace(teachedPlace).build());
        // 4.给老师发邮件
        mailUtil.sendMail(email,"骏波在线教育","您已注册成功，教工号为：" + teacherCode + " 需要等管理员审核过后才能登录哦，请您耐心等待！");
        return new Result(BaseMassage.REGISTER_SUCCESS,null,Result.SUCCESS_CODE);
    }

    @RequestMapping(value = "/zxjx/logout",method = {RequestMethod.GET,RequestMethod.POST})
    public Result logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        // 回到主页
        return Result.builder().code(Result.SUCCESS_CODE).build();
    }
}
