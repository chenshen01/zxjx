package com.zxjx.mapper;

import com.zxjx.base.BaseMapper;
import com.zxjx.base.IBaseService;
import com.zxjx.entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 23:05
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {

    /**
     * <p>
     *查询老师上课的班级
     * </p>
     *
     * @param id 老师id
     * @return List<Classes>
     * @author liuzhixiang 2020/04/19 20:48
     */
    List<Classes> queryTeacherTeachingClasses(@Param("id")Long id);
}
