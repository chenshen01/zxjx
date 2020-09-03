package com.zxjx.service.impl;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Classes;
import com.zxjx.entity.Courses;
import com.zxjx.entity.Student;
import com.zxjx.entity.Teacher;
import com.zxjx.mapper.StudentMapper;
import com.zxjx.service.IAttachmentService;
import com.zxjx.service.IClassesService;
import com.zxjx.service.ICoursesService;
import com.zxjx.service.ITeacherService;
import com.zxjx.component.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 23:06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ClassesServiceImpl extends BaseServiceImpl<Classes> implements IClassesService {

    @Autowired
    private IAttachmentService attachmentService;
    
    @Value("${file.classes-image-location}")
    private String classesImageLocation;
    
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private ICoursesService coursesService;
    
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private StudentMapper studentMapper;
    
    /**
     * <p>
     *添加班级
     * </p>
     *
     * @param courseId 所属课程的id
     * @param request
     * @param classCapacity
     * @param classCode 班级代码唯一
     * @param classImage
     * @return 添加状态
     * @author liuzhixiang 2020/04/19 11:23
     */
    @Override
    public String addClasses(HttpServletRequest request, Long courseId, String classCode,
                             Integer classCapacity, MultipartFile classImage){
        // 1、校验班级代码是否重复
        List<Classes> classes = this.selectByCondition(Classes.builder().classCode(classCode).build());
        if (classes == null || !classes.isEmpty()) {
            return BaseMassage.ADD_CLASS_CODE_DUP;
        }
        // 2、创建班级
        String uploadUrl = attachmentService.upload(classImage, request, classesImageLocation);
        int insert = this.insert(Classes.builder().classCode(classCode).classStatus(Classes.CLASS_NEW).classCapacity(classCapacity)
                .classImage(uploadUrl).courseId(courseId).build());
        if (insert > 0) {
            // 3、通知上这个的老师
            Courses courses = coursesService.selectById(Courses.builder().id(courseId).build());
            Teacher teacher = teacherService.selectById(Teacher.builder().id(courses.getTeachedBy()).build());
            mailUtil.sendMail(teacher.getEmail(),BaseMassage.SUBJECT,"您教授的" + courses.getCourseName() + "已经创建了班级，班级代码为"
                    + classCode);
            // 4、通知买了这个课的学生可以选课了
            List<Student> students = studentMapper.selectStudentByCourseId(courseId);
            if (students != null && !students.isEmpty()) {
                students.forEach( student ->
                    mailUtil.sendMail(student.getEmail(),BaseMassage.SUBJECT,"您购买的" + courses.getCourseName() + "已经创建了班级，班级代码为" +
                            classCode + "快去选课吧！"));
            }
        } else {
            return BaseMassage.ADD_CLASS_FAIL;
        }
        return BaseMassage.ADD_CLASS_SUCCESS;
    }

    /**
     * <p>
     * 删除班级
     * </p>
     *
     * @param classId
     * @return message
     * @author liuzhixiang 2020/05/12 13:24
     */
    @Override
    public String deleteClass(Long classId){
        Classes classes = this.selectById(Classes.builder().id(classId).build());
        Courses courses = coursesService.selectById(Courses.builder().id(classes.getCourseId()).build());
        Date curDate = new Date();
        // 课程未开始
        if (curDate.before(courses.getCourseStart())) {
            return BaseMassage.DELETE_CLASS_FAIL_2;
        }
        // 课程进行中
        if (curDate.after(courses.getCourseStart())&&curDate.before(courses.getCourseEnd())) {
            return BaseMassage.DELETE_CLASS_FAIL_3;
        }
        // 课程结束
        this.deleteByPrimaryKey(classes);
        return BaseMassage.DELETE_SUCCESS;
    }
}
