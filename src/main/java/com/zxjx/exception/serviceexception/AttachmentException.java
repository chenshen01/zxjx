package com.zxjx.exception.serviceexception;

/**
 * description
 *
 * @author liuzhixiang 2020/05/25 9:49
 */
public class AttachmentException extends ServiceException {
    private static final long serialVersionUID = -8400239090334588012L;

    public static final String RESUME_UPLOAD_ERROR = "简历上传异常";
    public static final String ATTACHMENT_LOCATION_UNKNOWN = "请指定文件上传位置";
    public static final String ATTACHMENT_UPLOAD_ERROR = "文件上传出错";
    public static final String DOWNLOAD_ATTACHMENT_IS_EMPTY = "下载的文件不存在";
    public static final String UPLOAD_ATTACHMENT_IS_EMPTY = "上传的文件不存在";
    public static final String DOWNLOAD_ATTACHMENT_ERROR = "文件下载异常";

    public AttachmentException(String message){
        super(message);
    }

    public AttachmentException(int code,String message){
        super(code,message);
    }
}
