package com.zxjx.mapper;

import com.zxjx.base.BaseMapper;
import com.zxjx.entity.CourseClass;
import com.zxjx.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 19:53
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    /**
     * <p>
     * 查询购买了这个课的学生
     * </p>
     *
     * @param courseId 课程id
     * @return  List<Student>
     * @author liuzhixiang 2020/04/28 11:56
     */
    List<Student> selectStudentByCourseId(@Param("courseId") Long courseId);
}
