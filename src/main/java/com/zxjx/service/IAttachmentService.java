package com.zxjx.service;

import com.zxjx.base.IBaseService;
import com.zxjx.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:52
 */
public interface IAttachmentService  extends IBaseService<Attachment> {
    /**
     * <p>
     *上传头像
     * </p>
     *
     * @param request
     * @param headImage
     * @return 存储路径
     * @author liuzhixiang 2020/04/07 23:16
     */
    String uploadHeadImage(MultipartFile headImage , HttpServletRequest request);

    /**
     * <p>
     *上传简历
     * </p>
     *
     * @param request
     * @param resume 简历
     * @return 存储路径
     * @author liuzhixiang 2020/04/08 11:50
     */
    String uploadResume(MultipartFile resume , HttpServletRequest request);

    /**
     * <p>
     *个人简历下载
     * </p>
     *
     * @param  response
     * @param fileName 下载的文件名
     * @return 下载结果
     * @author liuzhixiang 2020/04/11 13:14
     */
    String downloadResume(HttpServletResponse response , String fileName);

    /**
     * <p>
     *文件上传通用方法
     * </p>
     *
     * @param request
     * @param file
     * @param location 要上传位置
     * @return 图片的绝对路径
     * @author liuzhixiang 2020/04/15 22:58
     */
    String upload(MultipartFile file , HttpServletRequest request,String location);
}
