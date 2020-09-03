package com.zxjx.service.impl;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Classes;
import com.zxjx.entity.Courses;
import com.zxjx.entity.SelectCourse;
import com.zxjx.service.IClassesService;
import com.zxjx.service.ICoursesService;
import com.zxjx.service.ISelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/04 12:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SelectCourseServiceImpl extends BaseServiceImpl<SelectCourse> implements ISelectCourseService {

    @Autowired
    private IClassesService classesService;

    @Autowired
    private ICoursesService coursesService;
    /**
     * <p>
     * 选课
     * </p>
     *
     * @param courseId
     * @param classId
     * @param studentId
     * @return message
     * @author liuzhixiang 2020/05/11 10:50
     */
    @Override
    public String selectClass(Long studentId,Long courseId,Long classId){
        // 1、校验时间段
        List<SelectCourse> selectCourses = this.selectByCondition(SelectCourse.builder().selectedBy(studentId).build());
        Courses courses = coursesService.selectById(Courses.builder().id(courseId).build());
        if (selectCourses != null && !selectCourses.isEmpty()) {
            selectCourses.forEach(selectCourse ->
                checkTime(Courses.builder().id(selectCourse.getCourseId()).build(),courses)
            );
        }
        // 2、插入选课表
        this.insert(SelectCourse.builder().classId(classId).courseId(courseId).selectedBy(studentId).build());
        // 3、修改班级已选人数
        Classes classes = classesService.selectById(Classes.builder().id(classId).build());
        Integer studentAccount = classes.getStudentAccount() == null ? 0 : classes.getStudentAccount();
        classes.setStudentAccount(studentAccount + 1);
        classesService.updateByPrimaryKey(classes);
        return BaseMassage.SELECT_CLASS_SUCCESS;
    }

    /**
     * <p>
     *已经选了的课程
     * </p>
     *
     * @author liuzhixiang 2020/05/12 11:18
     */
    private String checkTime(Courses selectedCourse,Courses courses){

        return null;
    }
}
