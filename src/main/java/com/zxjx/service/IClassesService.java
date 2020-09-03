package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.Classes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 23:05
 */
public interface IClassesService extends IBaseService<Classes> {

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
    String addClasses(HttpServletRequest request,Long courseId,String classCode,
                      Integer classCapacity,MultipartFile classImage);

    /**
     * <p>
     * 删除班级
     * </p>
     *
     * @param classId
     * @return message
     * @author liuzhixiang 2020/05/12 13:24
     */
    String deleteClass(Long classId);
}
