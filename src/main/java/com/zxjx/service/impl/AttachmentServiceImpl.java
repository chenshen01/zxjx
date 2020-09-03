package com.zxjx.service.impl;

import com.zxjx.base.BaseMassage;
import com.zxjx.base.BaseServiceImpl;
import com.zxjx.entity.Attachment;
import com.zxjx.exception.serviceexception.AttachmentException;
import com.zxjx.service.IAttachmentService;
import com.zxjx.utils.FileUtil;
import com.zxjx.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * description
 *
 * @author liuzhixiang 2020/04/03 22:53
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment> implements IAttachmentService {

    @Value("${file.head-image-location}")
    private String headImageLocation;

    @Value("${file.resume-image-location}")
    private String resumeLocation;

    @Value("${file.download-image-location}")
    private String downloadLocation;
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
    @Override
    public String uploadHeadImage(MultipartFile headImage , HttpServletRequest request){
        log.debug(headImage.getOriginalFilename() + "头像上传中");
        try {
            InputStream inputStream = headImage.getInputStream();
            String headImageName = headImage.getOriginalFilename();
            save(inputStream,headImageName,headImageLocation);
            return FileUtil.getRelativeLocation(headImageLocation + headImageName);
        } catch (IOException e){
            throw new AttachmentException(AttachmentException.ATTACHMENT_UPLOAD_ERROR);
        }
    }

    /**
     * <p>
     *上传简历
     * </p>
     *
     * @author liuzhixiang 2020/04/08 11:50
     */
    @Override
    public String uploadResume(MultipartFile resume , HttpServletRequest request){
        log.debug(resume.getOriginalFilename() + "简历上传中");
        try {
            InputStream inputStream = resume.getInputStream();
            String resumeName = resume.getOriginalFilename();
            save(inputStream,resumeName,resumeLocation);
            return resumeLocation + resumeName;
        } catch (IOException e){
            throw new AttachmentException(AttachmentException.RESUME_UPLOAD_ERROR);
        }
    }

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
    @Override
    public String upload(MultipartFile file , HttpServletRequest request,String location){
        if (StringUtils.isEmpity(location)) {
            throw new AttachmentException(AttachmentException.ATTACHMENT_LOCATION_UNKNOWN);
        }
        log.debug(file.getOriginalFilename() + "文件上传中");
        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            save(inputStream,filename,location);
            return FileUtil.getRelativeLocation(location + filename);
        } catch (IOException e){
            throw new AttachmentException(AttachmentException.ATTACHMENT_UPLOAD_ERROR);
        }
    }

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
    @Override
    public String downloadResume(HttpServletResponse response , String fileName){
        if (fileName == null || fileName == "") {
            throw new AttachmentException(AttachmentException.DOWNLOAD_ATTACHMENT_IS_EMPTY);
        }
        File file = new File(fileName);
        log.debug("正在下载" + file.getName());
        if (file.exists()) {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;fileName=" + fileName);
            byte [] bytes = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int length = bis.read(bytes);
                while (length != -1) {
                    outputStream.write(bytes,0,length);
                    length = bis.read(bytes);
                }
                return BaseMassage.FILE_DOWNLOAD_SUCCESS;
            } catch (IOException e){
                  throw new AttachmentException(AttachmentException.DOWNLOAD_ATTACHMENT_ERROR);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return BaseMassage.FILE_DOWNLOAD_ERROR;
    }
    private void save(InputStream inputStream,String fileName,String location) throws IOException{
        File file = new File(location);
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(location + "/" + fileName);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (IOException e){
            throw new AttachmentException(AttachmentException.ATTACHMENT_UPLOAD_ERROR);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    private String getDownloadFileName(String fileName){
        String[] split = fileName.split("/");
        if (split.length == 1) {
            return split[0];
        } else {
            return split[split.length - 1];
        }
    }
}
